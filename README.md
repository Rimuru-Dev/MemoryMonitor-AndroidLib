# Release Notes: MemoryMonitor-AndroidLib v1.0.0 (11.01.2025)

## 📦 Overview
MemoryMonitor-AndroidLib v1.0 — это первая версия Java-библиотеки для мониторинга оперативной и внутренней/внешней памяти Android-устройств. Эта библиотека предназначена для интеграции с Unity через `.aar` файл, предоставляя разработчикам простой и удобный API для работы с памятью устройства.

---

## 🛠 Features

### 1. **RAM Monitoring**
- Получение доступной оперативной памяти:
  - `RAMMonitor.GetAvailableRAM(Context context)` → `long`
- Получение общей оперативной памяти:
  - `RAMMonitor.GetTotalRAM(Context context)` → `long`
- Процент доступной оперативной памяти:
  - `RAMMonitor.GetAvailableRAMPercentage(Context context)` → `float`
- Проверка, находится ли устройство в режиме низкой памяти:
  - `RAMMonitor.IsLowRAM(Context context)` → `boolean`
- Рекомендация по очистке оперативной памяти:
  - `RAMMonitor.SuggestMemoryCleanup(Context context)` → `String`

---

### 2. **Internal Storage Monitoring**
- Получение доступного объёма внутренней памяти:
  - `StorageMonitor.GetAvailableStorage()` → `long`
- Получение общего объёма внутренней памяти:
  - `StorageMonitor.GetTotalStorage()` → `long`
- Процент доступной внутренней памяти:
  - `StorageMonitor.GetAvailableStoragePercentage()` → `float`

---

### 3. **External Storage Monitoring**
- Получение доступного объёма внешней памяти (SD-карты):
  - `StorageMonitor.GetAvailableExternalStorage()` → `long`
- Получение общего объёма внешней памяти:
  - `StorageMonitor.GetTotalExternalStorage()` → `long`
- Процент доступной внешней памяти:
  - `StorageMonitor.GetAvailableExternalStoragePercentage()` → `float`
- Тип внешней памяти (например, "Removable"):
  - `StorageMonitor.GetExternalStorageType()` → `String`

---

### 4. **API Level Management**
- Проверка уровня API устройства:
  - `SDKMonitor.ReadSDKVersion()` → `int`
- Проверка поддержки функционала на текущем уровне API:
  - `SDKMonitor.HandleFeatureSupport(int requiredApiLevel)` → `int`

---

## 📝 Notes
- **Минимальный уровень API**: Android 6.0 (API Level 23)
- **Интеграция**: Используйте `AndroidJavaClass` в Unity для вызова методов библиотеки.
- **Пример использования**:
  ```csharp
  var sdkMonitor = new AndroidJavaClass("com.abyssmoth.mobilememorymonitor.SDKMonitor");
  int apiLevel = sdkMonitor.CallStatic<int>("ReadSDKVersion");
  Debug.Log($"API Level: {apiLevel}");
  ```

---

## 🚀 Getting Started
1. Загрузите файл `MemoryMonitor-AndroidLib-v1.0.aar`.
2. Добавьте его в проект Unity (папка `Plugins/Android`).
3. Используйте `AndroidJavaClass` или `AndroidJavaObject` для вызова методов.

---

## 🔧 Known Limitations
1. Для получения данных о внешней памяти пользователю может понадобиться предоставить разрешение `READ_EXTERNAL_STORAGE`.
2. Методы, работающие с внешней памятью, возвращают `0` или `"Not Available"`, если SD-карта отсутствует или недоступна.

---
