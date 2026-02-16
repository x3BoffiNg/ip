# BoffBot User Guide

![Ui](Ui.png)

BoffBot is a GUI-based task management chatbot designed to help users manage their daily tasks efficiently.  
It supports adding todos, deadlines, and events, along with marking, deleting, searching, and reminder features.

All tasks are automatically saved and loaded from local storage.

---

# Features

---

## Adding a Todo

Adds a simple task without a date.

### Format

`todo <description>`

### Example

`todo read book`

### What it does

Creates a new task with the given description and marks it as incomplete.

If the description is empty, an error message will be shown.

---

## Adding a Deadline

Adds a task with a due date.

### Format

`deadline <description> /by <yyyy-MM-dd>`

### Example

`deadline submit assignment /by 2024-12-01`

### What it does

Creates a deadline task with the specified due date.

The date must follow the format `yyyy-MM-dd`.  
If the date format is invalid, an error message will be displayed.

---

## Adding an Event

Adds a task with a start and end date.

### Format

`event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`

### Example

`event project meeting /from 2024-11-10 /to 2024-11-12`

### What it does

Creates an event with a start and end date.

Both dates must follow the format `yyyy-MM-dd`.  
The end date cannot be before the start date.

---

## Listing Tasks

Displays all current tasks.

### Format

`list`

### What it does

Shows all tasks in numbered order.

If there are no tasks, BoffBot will inform the user that the task list is empty.

---

## Marking a Task

Marks a task as completed.

### Format

`mark <task number>`

### Example

`mark 1`

### What it does

Marks the specified task as done.

If the task number is invalid, an error message will be shown.

---

## Unmarking a Task

Marks a task as not completed.

### Format

`unmark <task number>`

### Example

`unmark 1`

### What it does

Marks the specified task as not done.

---

## Deleting a Task

Removes a task from the list.

### Format

`delete <task number>`

### Example

`delete 2`

### What it does

Deletes the specified task and updates the task count.

---

## Finding Tasks

Searches for tasks containing a keyword.

### Format

`find <keyword>`

### Example

`find book`

### What it does

Displays all tasks whose descriptions contain the given keyword.

If no matching tasks are found, BoffBot will inform the user.

---

## Reminding Upcoming Deadlines

Shows deadlines that are due tomorrow.

### Format

`remind`

### What it does

Displays all deadline tasks due the next day.

If there are no deadlines due tomorrow, BoffBot will indicate that none are found.

---

## Exiting the Application

Closes the chatbot session.

### Format

`bye`

---

# Data Storage

All tasks are saved automatically to:

`data/boffbot.txt`

When BoffBot starts, it loads previously saved tasks automatically.

---

# Error Handling

BoffBot handles common errors gracefully, including:

- Missing descriptions  
- Invalid task numbers  
- Incorrect date formats  
- Incorrect command syntax  
- Empty task list  
- Missing data file  

Clear error messages are displayed in the GUI to guide the user.
