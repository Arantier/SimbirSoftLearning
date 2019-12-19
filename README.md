# Программа обучения Android-разработчика
###### Туториал по изучению теоретических материалов:
Обозначение    | Уровень важности
--------|-------------------------------------------------------------------------------
**(\*\*\*\*)**   | Без изучения этого материала невозможно успешное прохождение темы
**(\*\*\*)**     | Материал, без которого сложно успешно завершить изучение темы
**(\*\*)**       | Важный материал, рекомендуемый к изучению
**(\*)**         | Полезная литература, улучшающая навыки
---

### Прохождение программы обучения
 Программа обучения разделена на секции. Каждая секция состоит из
 + Теоретической части;
 + Практической части;
 + Теста;

 Каждая секция начинается с выполнения практического задания. Теоретический материал изучается по мере необходимости для выполнения практики. После завершения практического задания необходимо в github создать merge request на ментора, чтобы он смог проверить задание. Если задание выполнено успешно, то ментор предоставляет тест по пройденной секции. Для успешного прохождения теста в большинстве случаев достаточно знаний, полученных в ходе выполнения практического задания и прочтения необходимой для него теории.

 Стоит отметить, что ментор в силу различных обстоятельств не всегда может оперативно проверять merge request'ы и предоставлять тесты. Поэтому, если ментор вам говорит, что сможет проверить задание/предоставить тест только через несколько часов - приступайте к выполнению следующей секции программы обучения.
 **Важно** одновременно непроверенным может быть не более одной секции программы обучения. То есть, чтобы приступить к 5ой секции, Ваше практическое задание по 3ей секции должно быть одобрено, а тест пройден.


 В случае возникновения вопросов во время выполнения практического задания, можно просить помощи у ментора. Однако не стоит подходить к ментору с недекомпозированной задачей из разряда "Я не понимаю, как сверстать экран". Декомпозируйте задачу, чтобы задать ментору более конкретный вопрос. Также не стоит сразу же спрашивать ментора, как только возникла трудность. Для начала попробуйте самостоятельно найти ответ на свой вопрос в интернете.
 
 ---
## I. Верстка
---
### Теоретическая часть

В случае если по ссылке встречается пошаговый гайд - рекомендуется его выполнить в отдельном проекте.

**1. Начало разработки под Android**
+ [Начало разаработки](https://developer.android.com/training/index.html) **(\*\*\*\*)**

**2. Верстка**
+ [Уроки верстки из курсов](http://startandroid.ru/ru/uroki/vse-uroki-spiskom.html) **(\*\*\)**
+ [Создание макетов в XML и View groups](https://developer.android.com/guide/topics/ui/declaring-layout.html) **(\*\*\*)**

**3. Типы layout'ов**
+ [Frame Layout](http://developer.alexanderklimov.ru/android/layout/framelayout.php) **(\*\*\)**
+ [Linear Layout](https://developer.android.com/guide/topics/ui/layout/linear.html) **(\*\*\)**
+ [Relative Layout](https://developer.android.com/guide/topics/ui/layout/relative.html) **(\*\*\)**

**4. Табы**
+ [Обзор](https://developer.android.com/training/implementing-navigation/lateral.html) **(\*\*\*)**

**5. BottomNavigationView**
+ [Обзор](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html) **(\*\*\*)**

**6. Constraint Layout**
+ [Документация](https://developer.android.com/reference/android/support/constraint/ConstraintLayout.html) **(\*\*\*\*)**
+ [Работа с различными свойствами](https://habrahabr.ru/company/touchinstinct/blog/326814/) **(\*\*\*\*)**

**7. Списки**
+ [ListView](http://developer.alexanderklimov.ru/android/views/listview.php) **(\*\*\)**
+ [RecyclerView и Adapter](https://developer.android.com/training/material/lists-cards.html) **(\*\*\)**
+ [DiffUtils](https://medium.com/@iammert/using-diffutil-in-android-recyclerview-bdca8e4fbb00) **(\*\*\)**

**8. Ресурсы**
+ [Шрифты в XML](https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml.html) **(\*\*\)**
+ [Загружаемые шрифты](https://developer.android.com/guide/topics/ui/look-and-feel/downloadable-fonts.html) **(\*\*\)**
+ [Поддержка разных экранов](https://developer.android.com/guide/practices/screens_support.html) **(\*\*\)**
+ [Zeplin](https://habrahabr.ru/company/uteam/blog/315542/) **(\*\*\)**

**Важно** В компании при разработке любого мобильного приложения считается правилом хорошего тона придерживаться нефункциональных требований, описанных в [данной статье](http://kb.simbirsoft/nonfunctional-support/)

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `layouts` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. В системе PS также необходимо создать созвучную задачу, в которую после завершения будет залогировано время.

1. Добавить иконки приложения и сделать на экране отображаемое название приложения "Хочу помочь". Ресурсы иконок [тут](https://zpl.io/2jkoMOp).
2. Реализовать Splash Screen согласно [макету](https://zpl.io/2jlk3Mm).
3. Создать экран "Категории помощи" приложения согласно [макету](https://zpl.io/b6lYE9d).
 - Экран "Категории помощи" должен быть показан после Splash Screen. По нажатию по стрелки назад, приложение закрывается.
 - Необходимо реализовать нижний элемент навигации с помощью самописного решения согласно макету (только верстку без реализации логики навигации)
 - Экран должен представлять из себя activity с `RecyclerView`.
 - Верстка должна быть реализована в xml.
 - Верстка должна быть выполнена с учетом "pixel perfect" - когда все элементы дизайна расположены и имеют размеры абсолютно идентичные макету для экрана с теми же размерами что и макет и адекватно масштабироваться для других размеров и разрешений.
 - Все переиспользуемые размеры в xml должны быть вынесены в dimes, цвета в colors, а строки в strings.
 - Никаких "магических чисел", все должно иметь понятные названия
4. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## II. Android OS. Activity. Fragments
---
### Теоретическая часть

**1. Android OS:** 
+ [История Android](https://www.android.com/history/#/marshmallow) **(\*\*)**
+ [Архитектура Android](https://source.android.com/devices/architecture/) **(\*\*)**

**2. Application:**  
+ [Application](https://developer.android.com/reference/android/app/Application.html)  **(\*\*\*)**
+ [Context](https://possiblemobile.com/2013/06/context/)  **(\*\*\*)**
+ [Файл Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro.html) **(\*\*\*\*)**

**3. Activity:**  
+ [Activity - основы](https://developer.android.com/guide/components/activities.html) **(\*\*\*\*)**
+ [Task и Back Stack](https://habrahabr.ru/post/186434/) **(\*\*)**
+ [Передача данных между Activity](https://developer.android.com/guide/components/activities/parcelables-and-bundles.html) **(\*\*\*\*)**
+ [Управление жизненным циклом Activity](https://developer.android.com/training/basics/activity-lifecycle/index.html) **(\*\*\*\*)**

**4. Fragment:**  
+ [Fragment - основы](https://developer.android.com/guide/components/fragments.html) **(\*\*\*\*)**
+ [Диалоговые окна](https://developer.android.com/guide/topics/ui/dialogs.html) **(\*\*\*\*)**
+ [Обработка изменений конфигурации экрана](https://developer.android.com/guide/topics/resources/runtime-changes.html?hl=ru) **(\*\*\*\*)**
+ [Target fragment](https://habrahabr.ru/post/259805/) **(\*\*)**


**5. Работа со сторонними приложениями и permissions:**  
+ [Run-time permissions](https://developer.android.com/training/permissions/requesting.html)**(\*\*\*\*)**
+ [Intent и фильтры](https://developer.android.com/guide/components/intents-filters.html?hl=ru)**(\*\*\*\*)**
+ [Взаимодействие с другими приложениями](https://developer.android.com/training/basics/intents/index.html )**(\*\*\*)**

**6. BroadcastReceiver:**  
+ [BroadcastReceiver - основы](http://codetheory.in/android-broadcast-receivers/) **(\*\*\*\*)**
+ [Изменения работы с BroadcastReceiver с Android 8.0](https://developer.android.com/guide/components/broadcast-exceptions.html) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `fragments` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Реализовать экран профиль согласно [макету](https://zpl.io/b6lQpZq)
2. Реализовать диалог согласно [макету](https://zpl.io/brkmRYX)
 - Диалог должен открываться при нажатии на изображение пользователя на экране профиля 
3. Реализовать экран поиска согласно [макету](https://zpl.io/bAGAPj8)
 - Экран должен быть построен с использованием `ViewPager` и фрагментов
 - Должна быть реализована возможность изменять выбранную вкладку перелистыванием с плавной анимацией. 
 - В качестве названий для результатов должны использоваться произвольные случайные строки 
 - Данные для отображения результата должны генерироваться случайным образом при каждом перелистывании `ViewPager`. 
4. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## III. Структуры данных. Работа с файлами
---
### Теоретическая часть

**1. Работа с файлами в java. Сериализация:**  
+ [Потоки ввода-вывода](https://metanit.com/java/tutorial/6.1.php)  **(\*\*\*\*)**
+ [Закрытие потоков](https://metanit.com/java/tutorial/6.2.php)  **(\*\*\*\*)**
+ [Чтение и запись файлов. FileInputStream и FileOutputStream](https://metanit.com/java/tutorial/6.3.php)  **(\*\*\*\*)**
+ [Классы ByteArrayInputStream и ByteArrayOutputStream](https://metanit.com/java/tutorial/6.4.php)  **(\*\*)**
+ [Буферизуемые потоки. Классы BufferedInputStream и BufferedOuputStream](https://metanit.com/java/tutorial/6.5.php)  **(\*\*\*\*)**
+ [Чтение и запись текстовых файлов. FileReader и FileWriter](https://metanit.com/java/tutorial/6.8.php)  **(\*\*)**
+ [Буферизируемые символьные потоки. BufferedReader и BufferedWriter](https://metanit.com/java/tutorial/6.9.php)  **(\*\*)**
+ [Сериализация объектов](https://metanit.com/java/tutorial/6.10.php)  **(\*\*)**
+ [Класс File. Работа с файлами и каталогами](https://metanit.com/java/tutorial/6.11.php)  **(\*\*\*\*)**
+ [Работа с ZIP-архивами](https://metanit.com/java/tutorial/6.12.php)  **(\*\*)**

**2. JSON:**
+ [Понятие](https://ru.wikipedia.org/wiki/JSON)  **(\*\*\*\*)**
+ [Парсинг в Android](https://metanit.com/java/android/13.3.php)  **(\*\*\*)**
+ [Gson](https://habrahabr.ru/company/naumen/blog/228279/) **(\*\*\*\*)**

**3. Сохранение данных в файловую систему Android:**  
+ [SharedPreferences](https://developer.android.com/training/basics/data-storage/shared-preferences.html?hl=ru#GetSharedPreferences) **(\*\*\*\*)**
+ [Настройки через Preferences](https://developer.android.com/guide/topics/ui/settings.html?hl=ru) **(\*\*)**
+ [Android data storage](https://developer.android.com/training/basics/data-storage/files.html) **(\*\*\*\*)**
+ [FileProvider](https://developer.android.com/reference/android/support/v4/content/FileProvider.html) **(\*\*\*)**

**3. Работа с датой и временем:**  
+ [Date, Calendar](http://developer.alexanderklimov.ru/android/java/date.php) **(\*\*\*\*)**
+ [Date в Java 8](http://www.baeldung.com/java-8-date-time-intro) **(\*\*\*\*)**
+ [Работа со временем в java ](https://habrahabr.ru/post/274811/) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `data_structures` и все изменения пушить в нее. После завершения работы над задачей в gitlab необходимо создать merge request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style. Верстка экранов должна быть выполнена по принципу pixel-perfect.

1. Подключить к проекту [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP). Все операции с датой и временем должны быть реализованы через классы данной библиотеки.
2. Сверстать экран "Благотворительных событий" согласно [макету](https://zpl.io/brkm3we). Переход на этот экран осуществляется при выборе любой категории на экране "Категории помощи".
3. Сверстать экран "Детальное описание события" согласно [макету](https://zpl.io/adA93Z5). Переход на этот экран осуществляется при выборе любого события из списка, данный экран должен получить информацию о том, какое событие было выбрано на предыдущем шаге.
4. Необходимо создать два json'а. Содержащих в себе массивы категорий и благотворительных событий. Информация об объектах должна быть достаточной для формирования отображений на экранах, а также для корректного разделения по категориям помощи. Каждый объект должен обладать уникальным (среди объектов своего типа) идентификатором. Проверить корректность созданных json-ов через [online-parser](http://json.parser.online.fr/). Записать их в 2 файла и поместить в папку assets проекта.
5. Необходимо создать сущности соответствующие понятиям Категория и Событие.
6. Создать класс, который будет читать созданные json из файлов, парсить их и преобразовывать в массивы.
7. Наполнить экраны полученными данными. В сервисе учесть возможность фильтрации по категориям.
8. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## IV. Многопоточность
---
### Теоретическая часть

**1. Базовые понятия:**
+ [Определение](https://ru.wikipedia.org/wiki/%D0%9C%D0%BD%D0%BE%D0%B3%D0%BE%D0%BF%D0%BE%D1%82%D0%BE%D1%87%D0%BD%D0%BE%D1%81%D1%82%D1%8C)  **(\*\*\*\*)**
+ [Мьютекс](https://ru.wikipedia.org/wiki/%D0%9C%D1%8C%D1%8E%D1%82%D0%B5%D0%BA%D1%81)  **(\*\*\)**
+ [Семафор](https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D0%BC%D0%B0%D1%84%D0%BE%D1%80_(%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B0))  **(\*\*\)**
+ [Дэдлок](https://ru.wikipedia.org/wiki/Deadlock)  **(\*\*\*\)**
+ [Starvation and Livelock](https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html)  **(\*\*\)**

**2. Многопоточность в java:**
+ [Thread](https://habrahabr.ru/post/164487/) **(\*\*\*\*)**
+ [Синхронизация потоков. Оператор synchronized](https://metanit.com/java/tutorial/8.3.php) **(\*\*\*\*\)**
+ [Синхронизированные коллекции](https://habrahabr.ru/company/luxoft/blog/157273/)  **(\*\*\*)**
+ [Executors](http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/) **(\*\*\*\*)**

**3. Фоновая работа в Android:**
+ [Looper, Handler, and HandlerThread](https://blog.mindorks.com/android-core-looper-handler-and-handlerthread-bd54d69fe91a). [Видео](https://www.youtube.com/watch?v=gDvjU8HSuYE)  **(\*\*\*)**
+ [Loader](https://habrahabr.ru/company/e-Legion/blog/265405/) **(\*\*)**
+ [AsyncTask](https://developer.android.com/reference/android/os/AsyncTask.html)  **(\*\*\*)**
		
**4. Service:**
+ [Service - основы](https://developer.android.com/guide/components/services.html)  **(\*\*\*\*)**
+ [IntentService](http://developer.alexanderklimov.ru/android/theory/intentservice.php)  **(\*\*\*\*)**
+ [Job Scheduler](http://ticketmastermobilestudio.com/blog/how-to-use-androids-job-scheduler) **(\*\*\*\*)**
+ [Background Execution Limits Android 8.0+](https://developer.android.com/about/versions/oreo/background.html) **(\*\*)**


### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `concurrency` и все изменения пушить в нее. После завершения работы над задачей в github необходимо создать pull request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. В рамках предыдущего задания было реализовано чтение из файла. Перенести чтение из файла, парсинг, фильтрацию в background-поток. При повороте экрана не должно происходить повторное чтение из файла.
2. Реализовать Activity Indicator на экранах Категорий и Событий. Индикатор должен показываться с момента запроса данных до момента их отображения на экране. **Внимание!** Все действия c UI должны совершаться в главном потоке. 
3. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.

---
## V. Базы данных. Content Provider
---
### Теоретическая часть

**1. Android SQLite:**
+ [Работа с SQLite средствами Android SDK](https://developer.android.com/training/data-storage/sqlite.html)  **(\*\*\)**
+ [ORM](https://habrahabr.ru/company/yotadevices/blog/242559/)  **(\*\*\)**

**2. Основы Realm:**
+ [Документация по Realm](https://realm.io/docs/java/latest/)  **(\*\*\*\*)**

**3. Content Provider:**
+ [Основы](https://developer.android.com/guide/topics/providers/content-provider-basics.html?hl=ru) **(\*\*)**

### Практическое задание
Работа должна производится в созданном ранее проекте.

Все изменения должны быть закоммичены, а названия коммитов должны коротко и исчерпывающе описывать содержащие изменения. Каждый коммит должен быть рабочим, отправка некомпилирующегося кода недопустима. Для работы над этим заданием необходимо переключится на ветку `data_base` и все изменения пушить в нее. После завершения работы над задачей в github необходимо создать pull request в ветку `develop`.
Код должен быть читабельным и написан согласно code-style.

1. Необходимо создать базу данных с использованием Realm для хранения сущностей Категории и Благотворительных событий.
2. Реализовать на старте приложения чтение из файла и сохранение в БД.
 - Все "тяжелые" операции долны быть реализованы в фоновом потоке
 - На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
3. Наполнение экранов Категории и Благотворительных событий должно происходить из созданной базы данных
 - Все "тяжелые" операции долны быть реализованы в фоновом потоке
 - На время выполнения фоновых операций пользователю должен быть показан Activity Indicator
4. После завершения работ над задением отправить ментору количество затраченного времени на практическое выполнение задачи.
