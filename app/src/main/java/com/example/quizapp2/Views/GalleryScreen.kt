package com.example.quizapp2.Views

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kotlin.collections.emptyList
import androidx.compose.ui.platform.LocalContext
import com.example.quizapp2.Model.QuizObject
import com.example.quizapp2.ViewModel.QuizObjectViewModel

// gets the viewmodel.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(quizViewModel: QuizObjectViewModel = viewModel()) {

    // observe the list from the database
    val quizObjects by quizViewModel.allQuizObjects.observeAsState(emptyList())

    //persisting permission for the images
    val localContext = LocalContext.current

    // dialog state
    var showAddDialog by remember { mutableStateOf(false) }
    var pendingUri by remember { mutableStateOf<Uri?>(null) }
    var nameInput by remember { mutableStateOf("") }

    // photo picker
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            // take persistent permission so URI survives app restart
            localContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            pendingUri = uri
            nameInput = ""
            showAddDialog = true
        }
    }

    Scaffold(modifier = Modifier.background(Color(0xFFedede9)),
        containerColor = Color(0xFFedede9),
        topBar = {
            TopAppBar(
                title = { Text("Gallery") },
                actions = {
                    Button(onClick = {
                        photoPicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) { Text("Add") }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).background(Color(0xFFedede9))) {
            items(items = quizObjects, key = { it.id }) { quizObject ->
                GalleryRow(
                    quizObject = quizObject,
                    onDelete = { quizViewModel.removeQuizObject(it.name) }
                )
            }
        }
    }

    // dialog etter du har valgt bilde
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = {
                showAddDialog = false
                pendingUri = null
            },
            title = { Text("Add Quiz Object") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Enter a name for the selected photo.")
                    TextField(
                        value = nameInput,
                        onValueChange = { nameInput = it },
                        label = { Text("Name") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    enabled = pendingUri != null && nameInput.isNotBlank(),
                    onClick = {
                        val uri = pendingUri ?: return@Button
                        quizViewModel.insertQuizObject(
                            QuizObject(
                                name = nameInput.trim(),
                                imageUri = uri.toString()
                            )
                        )
                        showAddDialog = false
                        pendingUri = null
                    }
                ) { Text("Add") }
            },
            dismissButton = {
                Button(onClick = {
                    showAddDialog = false
                    pendingUri = null
                }) { Text("Cancel") }
            }
        )
    }
}
@Composable
fun GalleryRow(
    quizObject: QuizObject,
    onDelete: (QuizObject) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)

    ) {
        AsyncImage(
            model = quizObject.imageUri,
            contentDescription = quizObject.name,
            modifier = Modifier
                .size(64.dp)
                .clickable { onDelete(quizObject) },
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(12.dp))
        Text(text = quizObject.name)
    }
}