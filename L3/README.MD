# Java Technologies - Lab 3

Lab page - https://profs.info.uaic.ro/~acf/tj/labs/lab_03.html

The [Java Web Application project](https://github.com/StrugariStefan/JT/tree/main/L3/laborator3) was added for fulfilling the requirements described on the lab page. It contains the following components:
- *index.xhtml* page, contains the *Exam Scheduler Home Page*. The user can navigate from here to *examCreate.xhtml* or *studentCreate.xhtml* pages.
- *examCreate.xhtml* page, contains the *Exam Form*. The user can add a new exam.
- *ExamViewModel* request scoped managed bean, used for adding the exam to the database.
- *LocalDateTimeConverter*, used for converting the starting time input string to LocalDateTime before is set on the *ExamViewModel* bean.
- *studentCreate.xhtml* page, contains the *Student Form*. The user can add a new student.
- *StudentViewModel* request scoped managed bean, used for retriving the already registered exam list. The list will be used for selecting the exams that the student should attend. It is also used for adding the student to the database.
- *StudentRepository*, used for saving a student to the database.   
- *ExamRepository*, used for getting all the exams or saving an exam to the database.
- *ExamSchedulerService* persistance unit, used for retriving database connections for MSSQL database and also for creating the necesserary tables for persisting students and exams.  
- *language* resource bundle, used for internationalization. It supports two languages, english and french. The default one is english. It is used when setting text for buttons, labels or tab names in *index.xhtml*, *examCreate.xhtml* and *studentCreate.xhtml* pages.
