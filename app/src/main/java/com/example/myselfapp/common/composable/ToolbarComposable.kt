/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.myselfapp.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicToolbar(@StringRes title: Int) {
  TopAppBar(
    title = { Text(stringResource(title)) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = toolbarColor(),
      titleContentColor = MaterialTheme.colorScheme.onPrimary,
      actionIconContentColor = MaterialTheme.colorScheme.onPrimary
    )
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionToolbar(
  modifier: Modifier,
  @StringRes title: Int,
  @DrawableRes primaryActionIcon: Int,
  primaryAction: () -> Unit,
  @DrawableRes secondaryActionIcon: Int? = null,
  secondaryAction: (() -> Unit)? = null
) {
  TopAppBar(
    title = { Text(stringResource(title)) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = toolbarColor(),
      titleContentColor = MaterialTheme.colorScheme.onPrimary,
      actionIconContentColor = MaterialTheme.colorScheme.onPrimary
    ),
    actions = {
      Box(modifier) {
        Row(
          modifier = Modifier.wrapContentSize(),
        ) {
          IconButton(onClick = primaryAction) {
            Icon(
              painter = painterResource(primaryActionIcon),
              contentDescription = "Primary Action"
            )
          }
          if (secondaryAction != null && secondaryActionIcon != null) {
            IconButton(onClick = secondaryAction) {
              Icon(
                painter = painterResource(secondaryActionIcon),
                contentDescription = "Secondary Action"
              )
            }
          }
        }
      }
    }
  )
}

@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
  return if (darkTheme) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
}