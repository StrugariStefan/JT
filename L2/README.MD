# Java Technologies - Lab 2

Lab page - https://profs.info.uaic.ro/~acf/tj/labs/lab_02.html

The [Java Web Application project](https://github.com/StrugariStefan/JT/tree/main/L2/laborator2) was added for fulfilling the requirements described on the lab page. It contains the following components:
- *input.jsp* page, contains the *Word Form* and is used to invoke the *Controller* servlet. Sets the category dropdown with the value from a cookie if it is present in browser.
- *Controller* servlet, used for communicating with the business logic component and eventually forwarding *result.jsp* or *error.jsp* pages. Also sets the category in a cookie to be used by the user in the same session.
- business logic component, used for validating the word and inserting it in database. Also used for retrieving the list of words from the database.
- *result.jsp*, forwarded by the servlet when the business logic component succesfully saves a new word in the database. It contains the list of words inserted until now.
- *error.jsp*, forwarded by the servlet when the request contains form validation errors or business rules violation. It contains an error message.
- *CaptchaFilter*, the filter is used to filter out the requests that are made to *input.jsp*. Logs the request and generates a captcha to be used in the *Word form*. Also sets a session scoped attribute so that the *Controller* can validate the captcha.  
- *ResponseDecorator*, the filter is used for decorating the HTML responses with the prelude and the coda sections.
- *DefaultCategorySetterListener*, reads a context init parameter for the default category and sets it as an application scoped attribute. It will be used in the *Controller* if the request does not have the category set. 
