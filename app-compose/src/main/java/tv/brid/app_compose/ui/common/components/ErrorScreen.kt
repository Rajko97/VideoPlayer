package tv.brid.app_compose.ui.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            text = "Something Wrong Happened!\nCome back later!"
        )
    }
}

@Preview
@Composable
fun PreviewErrorScreen() {
    ErrorScreen(Modifier)
}