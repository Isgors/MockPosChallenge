package com.igordesouza.mockposchallenge.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.igordesouza.mockposchallenge.R

// Composable functions to get flavor-specific colors
@Composable
fun flavorPrimaryDark(): Color = colorResource(id = R.color.flavor_primary_dark)
@Composable
fun flavorSecondaryDark(): Color = colorResource(id = R.color.flavor_secondary_dark)
@Composable
fun flavorTertiaryDark(): Color = colorResource(id = R.color.flavor_tertiary_dark)


@Composable
fun flavorPrimaryLight(): Color = colorResource(id = R.color.flavor_primary_light)
@Composable
fun flavorSecondaryLight(): Color = colorResource(id = R.color.flavor_secondary_light)
@Composable
fun flavorTertiaryLight(): Color = colorResource(id = R.color.flavor_tertiary_light)
