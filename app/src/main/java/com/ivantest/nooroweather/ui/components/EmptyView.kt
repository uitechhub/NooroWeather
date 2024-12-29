package com.ivantest.nooroweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivantest.nooroweather.R


@Composable
fun EmptyView() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.empty_view_title),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(R.string.empty_view_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyViewPreview() {
    EmptyView()
}
