package com.example.core.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "bpc_settings")

class SettingsRepository(private val context: Context) {
    private val WEBSITE_URL = stringPreferencesKey("website_url")
    private val WC_CONSUMER_KEY = stringPreferencesKey("wc_consumer_key")
    private val WC_CONSUMER_SECRET = stringPreferencesKey("wc_consumer_secret")
    private val BPC_API_KEY = stringPreferencesKey("bpc_api_key")

    val websiteUrl: Flow<String?> = context.dataStore.data.map { it[WEBSITE_URL] }
    val wcConsumerKey: Flow<String?> = context.dataStore.data.map { it[WC_CONSUMER_KEY] }
    val wcConsumerSecret: Flow<String?> = context.dataStore.data.map { it[WC_CONSUMER_SECRET] }
    val bpcApiKey: Flow<String?> = context.dataStore.data.map { it[BPC_API_KEY] }

    suspend fun saveSettings(url: String, key: String, secret: String, bpcKey: String) {
        context.dataStore.edit {
            it[WEBSITE_URL] = url
            it[WC_CONSUMER_KEY] = key
            it[WC_CONSUMER_SECRET] = secret
            it[BPC_API_KEY] = bpcKey
        }
    }
    
    suspend fun clearSettings() {
        context.dataStore.edit { it.clear() }
    }
}
