# Java Technologies - Lab 5

Lab page - https://profs.info.uaic.ro/~acf/tj/labs/lab_05.html

The [Java Web Application project](https://github.com/StrugariStefan/JT/tree/main/L5/laborator5) was added for fulfilling the requirements described on the lab page. It contains all the components from the previous laboratory, and some additional or edited components:
- *examsCreate.xhtml* template client for *main.xhtml*, contains the exam form. At this moment the used can create exams of type *test* or *presentation*. The *test* type of exam has the *Bibliography* input field required and the *presentation* type of exam has the *Resources* input field required for creating the exam.
- *ExamsRepository* contains the definitions for all CRUD database operation plus a filtering method based on the Criteria API.
- *Exam* domain class entity is a base entity for *WrittenTest* and *ProjectPresentation* domain entities. The inheritance methodology used for those is *SINGLE_TABLE*.
- *ExamViewModel* creates different instances of type *Exam* based on the type of exam selected by the user from the *examsCreate.xhtml* page.
- *ExamsRepositoryTests* integration test class contains the test cases for *ExamsRepository* CRUD database operations.
- *MSSQLPool* connection pool and the *jdbc/MSSSQL* JDBC resource, configured on GlassFish server.
- *ExamSchedulerService* persistance unit. The *jdbc/MSSSQL* resource is configured as it's DataSource.
