# Android Project Setup: Adding API Key for WeatherAPI

This document provides instructions to configure this Android project with an API key required to access WeatherAPI services. The API key must be added to the `local.properties` file in your project. If the file does not exist, you will also find steps to create it.

---

## Steps to Get Your API Key

1. **Visit WeatherAPI Documentation:**
   Go to [WeatherAPI Documentation](https://www.weatherapi.com/docs/).

2. **Sign Up or Log In:**
    - If you don’t have an account, click **Sign Up** and follow the instructions to create a free account.
    - If you already have an account, log in with your credentials.

3. **Generate an API Key:**
    - After logging in, navigate to the **API Keys** section.
    - Click **Generate Key** and copy the generated API key.

---

## Adding the API Key to `local.properties`

### Locate or Create the `local.properties` File

1. **Locate the File:**
    - In your Android project’s root directory, look for a file named `local.properties`.
    - If the file exists, proceed to the next step.

2. **Create the File (If Necessary):**
    - If `local.properties` is missing, create one:
        1. Open the root directory of your project.
        2. Create a new file named `local.properties`.
        3. Ensure the file is saved in the project’s root (where `build.gradle` is located).

### Add the API Key Property

1. Open the `local.properties` file in a text editor.
2. Add the following line to include your WeatherAPI key:
   ```properties
   weatherApiKey=YOUR_API_KEY_HERE
   ```
   Replace `YOUR_API_KEY_HERE` with the key you obtained from the WeatherAPI website.
3. Save the file.

### Important Notes
- The `local.properties` file should **not** be committed to version control (e.g., Git). It is typically ignored by default in `.gitignore`. Verify that your `.gitignore` file includes `local.properties` to ensure your API key is not exposed.

---

By following these steps, you can securely integrate the WeatherAPI key into your Android project. If you encounter any issues, refer to the [WeatherAPI Documentation](https://www.weatherapi.com/docs/) or your project’s setup documentation.

