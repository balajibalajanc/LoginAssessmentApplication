#### ER Diagram

                    +-------------------+
                    |    Employee       |
                    +-------------------+
                    | Employee_ID (PK) |
                    | Employee_Name    |
                    | Department       |
                    | Designation      |
                    | Technology       |
                    | Reporting_Manager_ID (FK) |
                    +-------------------+
                           |
                           |
                           |
                           V
                    +-------------------+
                    |     Manager       |
                    +-------------------+
                    | Manager_ID (PK)  |
                    | Name             |
                    | Teams            |
                    | Reporting_To_SeniorVP_ID (FK) |
                    +-------------------+
