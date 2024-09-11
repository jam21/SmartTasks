package com.smarttasks.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun sizeMedium() = 10.dp
@Composable
fun sizeSmall() = 7.dp

@Composable
fun HorizontalMediumSpace(modifier: Modifier) = Spacer(modifier.height(sizeMedium()))

@Composable
fun HorizontalSmallSpace(modifier: Modifier) = Spacer(modifier.height(sizeSmall()))

@Composable
fun Modifier.mediumPadding() = padding(sizeMedium())

@Composable
fun Modifier.smallPadding() = padding(sizeSmall())