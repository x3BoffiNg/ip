# BoffBot User Guide

![Ui](./Ui.png)

BoffBot is a simple yet powerful task management chatbot with a graphical user interface (GUI).  
It helps users organize their tasks efficiently by supporting todos, deadlines, and events.

BoffBot allows users to:
- Add and manage tasks
- Mark and unmark tasks
- Delete tasks
- Search for tasks by keyword
- View tasks due tomorrow
- Automatically save and load tasks from file

Adding a Todo Task

The todo command allows users to add a simple task without any associated date or time. This is useful for general reminders or activities that do not have a fixed deadline. When a user enters the todo command followed by a description, BoffBot creates a new task with the given description and marks it as not completed by default. The task is then added to the task list and automatically saved to the storage file to ensure that it persists even after the application is closed.

For example, if a user enters:

todo read book

BoffBot will interpret this as a request to create a new task titled “read book.” The task will appear in the list as an uncompleted item, and the total number of tasks will increase accordingly. If the user attempts to create a todo without providing a description, BoffBot will display an appropriate error message prompting the user to provide valid input.

This feature enables users to quickly capture tasks in a minimal and efficient manner, helping them maintain an organized list of responsibilities within the application.


## Feature 

BoffBot provides a set of core task management features designed to help users organize and track their responsibilities efficiently. Users can create three types of tasks: simple todos, deadlines with a specific due date, and events with a defined start and end date. Each task can be marked as completed or uncompleted to reflect progress.

The application allows users to view all existing tasks in a numbered list format, making it easy to reference specific tasks for editing or deletion. Tasks can be deleted individually, and the total task count updates automatically to reflect changes.

BoffBot also supports searching for tasks using keywords, enabling users to quickly locate relevant tasks without manually scanning the entire list. Additionally, the reminder feature identifies deadlines that are due the next day, helping users stay on top of upcoming responsibilities.

All tasks are automatically saved to a local data file, ensuring that information is preserved between application sessions. Upon startup, previously saved tasks are loaded automatically. The application also includes error handling to manage invalid inputs, incorrect date formats, and invalid task numbers, ensuring that the system remains stable and user-friendly.


