# Java Technologies - Lab 4

Lab page - https://profs.info.uaic.ro/~acf/tj/labs/lab_04.html

The [Java Web Application project](https://github.com/StrugariStefan/JT/tree/main/L4/laborator4) was added for fulfilling the requirements described on the lab page. It contains all the components from the previous laboratory, and some additional components:
- *menuBar.xhtml* page, contains a menu. The user can navigate to *examsCreate.xhtml* or *studentsCreate.xhtml* pages using this menu.
- *header.xhtml* template, contains the menu and the page title.
- *footer.xhtml* page, contains the application version and the number of application's active sessions. This number is updated asynchronously.
- *main.xhtml* template, contains the the header, footer and a placeholder for the content.
- *examsCreate.xhtml* template client for *main.xhtml*, contains the exam form as content.
- *studentsCreate.xhtml* template client for *main.xhtml*, contains the student form as content.
- *VersionBean* application scoped bean, used for retriving the application version.
- *SessionListener*, used for counting the number of application's active sessions.
- *SessionBean* application scoped bean, used for setting the number of active sessions in the *footer.xhtml*.
- *MSSQLPool* connection pool and the *jdbc/MSSSQL* JDBC resource, configured on GlassFish server.
- *ExamSchedulerService* persistance unit. The *jdbc/MSSSQL* resource is configured as it's DataSource.
