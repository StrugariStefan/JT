# Java Technologies - Lab 1

Lab page - https://profs.info.uaic.ro/~acf/tj/labs/lab_01.html

The following applications and documentation were added for fulfilling the requirements described on the lab page:
- [Java Web Application project](https://github.com/StrugariStefan/JT/tree/main/L1/laborator1), which contains the following components:
    - *Key Value Form* page, used to invoke the *KeyValueServlet*.
    - *KeyValueServlet*, used for writing the data in a local file (*repository.txt*) and/or controlling the response returned to the user.
    - *mock.jsp*, forwarded by the servlet when the request is made with mock checkbox checked in *Key Value Form*. It contains a confirmation message.
    - *repository.jsp*, forwarded by the servlet when the request is made with mock checkbox unchecked. It contains the content of the *repository.txt* file.
    - *BrowserFilter*, the filter is used to filter out the requests that are made from a non-browser client. If the client is of non-browser type a simple message will be returned. The requests will not reach the components that were called.
    - *KeyValueFormValidationFilter*, the filter is used by the *KeyValueServlet* to filter out the requests that contain invalid parameters. The key should be a non-empty string. The value should be a positive integer. If these validations fail, *error.jsp* is forwarded and the *KeyValueServlet* is not reached. If the validations pass, a new instance of *KeyValueInputModel* is set on the request. The filter also logs the information about the request regardless of the validation result.
    - *error.jsp*, forwarded when the parameters provided to the *KeyValueServlet* are invalid. It contains error messages about the validation.
- [Python script](https://github.com/StrugariStefan/JT/blob/main/L1/key_value_form_client.py) for calling the *KeyValueServlet*.
- [JMeter project](https://github.com/StrugariStefan/JT/blob/main/L1/Key%20Value%20Form%20users.jmx) for measuring the performance of *KeyValueServlet*.
- [Performance analysis](https://github.com/StrugariStefan/JT/blob/main/L1/Performance%20Analysis.ipynb) for interpreting the performance results.
