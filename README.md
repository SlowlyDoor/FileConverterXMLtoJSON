# FileConverter
## Описание проекта
`FileConverter` - программа для конвертации данных между XML и JSON форматами
## Использование
Запуск происходит при помощи командной строки. Входными данными служат 2 аргумента:
1. Файл, находящийся в каталоге resources 
2. Название файла, который будет сохранён в каталоге resources
## ПО для сборки и запуска
Рекомендуемая версия Intellij Idea - 2023.2.3
## Структура проекта
- `src`: Основная папка проекта
  - `main`: Папка в которой находится основной код проекта
    - `java`: Классы необходимые для работы с xml и json файлами 
    - `resources`: Примеры данных xml и json
  - `test`: Тесты для программы
## Работа программы
1. Требуется скачать JDK версии 21 (x64 Compressed Archive), который можно скачать с:

    [https://www.oracle.com/java/technologies/downloads/#jdk21-windows](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)

3. **Клонирование репозитория**
   ```bash
    git clone https://github.com/SlowlyDoor/FileConverter.git
    cd FileConverter
   ```
## Пример использования
- Для конвертации `data.xml` в `dataConvert.json` необходимо:
```bash
java -jar  out\artifacts\FileConverter_jar\FileConverter.jar \your-path\to-file\data.xml \your-path\to-file\dataConvert.json
```
- Для конвертации `data.json` в `dataConvert.xml` необходимо:
```bash
java -jar  out\artifacts\FileConverter_jar\FileConverter.jar \your-path\to-file\data.json \your-path\to-file\dataConvert.xml
```
