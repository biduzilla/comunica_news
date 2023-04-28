package com.toddy.comunicanews.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.toddy.comunicanews.ui.activity.CHAVE_USER_NOME

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = CHAVE_USER_NOME)

val userNomePreferences = stringPreferencesKey(CHAVE_USER_NOME)
