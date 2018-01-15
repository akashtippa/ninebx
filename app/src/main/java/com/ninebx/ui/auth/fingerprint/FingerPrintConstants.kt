package com.ninebx.ui.auth.fingerprint

/**
 * Created by Alok on 15/01/18.
 */

@JvmField val DEFAULT_KEY_NAME = "default_key"

/**
 * Enumeration to indicate which authentication method the user is trying to authenticate with.
 */
enum class Stage { FINGERPRINT, NEW_FINGERPRINT_ENROLLED, PASSWORD }