# Java Technologies - Lab 6

Lab page - https://profs.info.uaic.ro/~acf/tj/labs/lab_06.html

The [Java Web Application project](https://github.com/StrugariStefan/JT/tree/main/L6/laborator6) was added for fulfilling the requirements described on the lab page. It contains all the components from the previous laboratory, and some additional or edited components:
- *reservation.xhtml* template client for *main.xhtml*, contains a form for attaching resources to an existing exam.
- *ResourceReservationViewModel* request scoped bean for mapping the input from the *reservation.xhtml* page. 
- *ResourcesRepository* contains the definitions the updating an existing resource and for retriving the existing resources
- *Resource* domain class entity contains the resource name and capacity.
- *ResourcesStatusService* EJB stateless bean. Retrieves the available resources and the exams that were not already assigned.
- *ResourcesAssignmentService* EJB statefull bean. Performs the update of the of the resource after the update is checked in the  *ResourcesAssignmentsMap*.
- *ResourcesAssignmentsMap* EJB singleton bean. Keeps an in memory map of the available capacity for all the resources.
