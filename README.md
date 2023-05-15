# CsvFileUploader
CSV File uploader. Spring Boot. Spring Web. H2 Database.

How to start the Project:

1. Run the project from the CsvFileUploaderApplication class.
2. Open localhost:8080
3. Select the .csv file you want to upload with user data
  3.1. NOTE: the file should contain 4 columns: "id", "name", "email" and "number".
  3.2. Records should look like this:
    id	name	email	number
    100	Atta Shah	atta@example.com	370-6-425
    101	Alex Jones	alex@example.com	370-6-111
    102	Jovan Lee	jovan@example.com	370-6-125
    103	Greg Hover	greg@example.com	370-86-12
4. Upload the file and you should get the result. If you want to test more records you can go back to the localhost:8080 and upload the file again. All the records are taken out from H2 database.

How to connect to the H2 database:

1. Run the project.
2. Open localhost:8080/h2-console
3. JDBC URL: jdbc:h2:mem:testdb
4. Press connect
5. There is automatically created customer table.
