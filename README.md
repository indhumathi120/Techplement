# Quiz Generator Application

Welcome to the Quiz Generator Application! This console application allows users to create, manage, and attend quizzes. It supports both `Admin` and `User` roles with different permissions.

## Description

This console application is a quiz generator that allows the creation and management of quizzes. It supports two user roles: `Admin` and `User`. 

- Admins can create, delete, update, attend, view, and get quizzes.
- Users can view and attend quizzes.

## Installation

1. Clone the repository:

    ```shell
    git clone https://github.com/indhumathi120/Techplement.git
    ```

2. Navigate to the project directory:

    ```shell
    cd quiz-generator
    ```

3. Build and compile the application:

    Follow the build instructions depending on the language and technology stack used Java.
   
4. Execute the application:

    Run the executable file or command depending on the language and environment.

## Usage

- **Admin Role**: You can create, delete, update, attend, and view quizzes.
- **User Role**: You can view and attend quizzes.

### Example Commands

- **Create a quiz**: `createQuiz`
- **View a quiz**: `viewQuiz`
- **Attend a quiz**: `attendQuiz`

Refer to the application for more specific commands and options.

## Roles and Permissions

- **Admin**:
    - Create, delete, attend, view, get, and update quizzes.
    - Manage quiz questions.
    
- **User**:
    - View and attend quizzes.

## Quiz Attributes

- **ID**: Unique identifier for the quiz.
- **Title**: Title of the quiz.
- **Conducted By**: The administrator or entity conducting the quiz.
- **Start Time**: Time when the quiz starts.
- **List of Questions**: Questions included in the quiz.
- **Total Marks**: Total marks for the quiz.

## Question Attributes

- **ID**: Unique identifier for the question.
- **Question Description**: The content of the question.
- **List of Options**: Choices available for the question.
- **Right Answer**: Correct answer for the question.

## User Attributes

- **Username**: User's login name.
- **Password**: User's password (encrypted).
- **Email**: User's email address.

## JSON Data Format

The application uses JSON files to feed questions for each category of the quiz. JSON files are loaded into objects using a JSON mapper. Here's an example of the JSON format:

```json
{
    "quiz_id": "1",
    "title": "Sample Quiz",
    "conducted_by": "Admin",
    "start_time": "2024-05-08T10:00:00",
    "questions": [
        {
            "id": "q1",
            "questionDescription": "What is the capital of France?",
            "options": ["Paris", "Lyon", "Marseille", "Nice"],
            "rightAnswer": "Paris"
        },
        {
            "id": "q2",
            "questionDescription": "What is 2 + 2?",
            "options": ["3", "4", "5", "6"],
            "rightAnswer": "4"
        }
    ],
    "total_marks": 10
}
