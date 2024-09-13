package com.smarttasks.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun smallSize() = 10.dp
@Composable
fun extraSmallSize() = 7.dp

@Composable
fun extraLargeSize() = 64.dp

@Composable
fun mediumSize() = 24.dp

@Composable
fun HorizontalMediumSpace(modifier: Modifier) = Spacer(modifier.height(smallSize()))

@Composable
fun HorizontalSmallSpace(modifier: Modifier) = Spacer(modifier.height(extraSmallSize()))

@Composable
fun Modifier.mediumPadding() = padding(smallSize())

@Composable
fun Modifier.smallPadding() = padding(extraSmallSize())
@Composable
fun Modifier.extraLargeWidth() = width(extraLargeSize())