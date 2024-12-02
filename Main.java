package org.example;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

class Student {
    String name;
    String id;
    List<String> coursesTaken;
    Map<String, Boolean> attendanceRecord;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.coursesTaken = new ArrayList<>();
        this.attendanceRecord = new HashMap<>();
    }

    public void addAttendance(String date, boolean present) {
        attendanceRecord.put(date, present);
    }

    public Map<String, Boolean> getAttendanceRecord() {
        return attendanceRecord;
    }

    public String toString() {
        return "Name: " + name + ", ID: " + id;
    }
}

class Course {
    String courseCode;
    String description;
    String prerequisites;
    String syllabus;
    String gradingScale;

    public Course(String courseCode, String description, String prerequisites, String syllabus, String gradingScale) {
        this.courseCode = courseCode;
        this.description = description;
        this.prerequisites = prerequisites;
        this.syllabus = syllabus;
        this.gradingScale = gradingScale;
    }

    public String toString() {
        return "Course Code: " + courseCode + "\nDescription: " + description + "\nPrerequisites: " + prerequisites
                + "\nSyllabus: " + syllabus + "\nGrading Scale: " + gradingScale;
    }
}

class Message {
    String sender;
    String recipient;
    String content;

    public Message(String sender, String recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public String toString() {
        return "From: " + sender + "\nTo: " + recipient + "\nMessage: " + content;
    }
}

class Announcement {
    String title;
    String content;

    public Announcement(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String toString() {
        return "Title: " + title + "\nContent: " + content;
    }
}
class StudentHub extends JFrame {
    private JPanel studentPanel;
    private JPanel coursePanel;
    private JPanel attendancePanel;
    private JPanel messagingPanel;
    private JTextArea studentTextArea, courseTextArea, attendanceTextArea, messageTextArea, announcementTextArea;
    private JTextField studentNameField, studentIdField, courseCodeField, descriptionField, prerequisitesField, syllabusField, gradingScaleField;
    private JTextField attendanceStudentField, attendanceDateField;
    private JTextField senderField, recipientField, messageContentField;
    private JTextField announcementTitleField, announcementContentField;
    private List<Student> studentList;
    private List<Course> courseList;
    private List<Message> messageList;
    private List<Announcement> announcementList;

    public StudentHub() {
        studentList = new ArrayList<>();
        courseList = new ArrayList<>();
        messageList = new ArrayList<>();
        announcementList = new ArrayList<>();

        setTitle("StudentHub");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadData();
        JTabbedPane tabbedPane = new JTabbedPane();

        studentPanel = createStudentPanel();
        coursePanel = createCoursePanel();
        attendancePanel = createAttendancePanel();
        messagingPanel = createMessagingPanel();
        JPanel announcementPanel = createAnnouncementPanel();
        Component createGradingPanel = createGradingPanel();


        tabbedPane.addTab("Student Management", studentPanel);
        tabbedPane.addTab("Course Management", coursePanel);
        tabbedPane.addTab("Attendance Tracking", attendancePanel);
        tabbedPane.addTab("Messaging System", messagingPanel);
        tabbedPane.addTab("Announcements", announcementPanel);
        tabbedPane.addTab("Grading System", createGradingPanel);

        add(tabbedPane);

        setVisible(true);
    }

    private void loadData() {
    }

    private JPanel createStudentPanel() {
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());

        // Input panel for student information
        JPanel studentInputPanel = new JPanel(new GridLayout(3, 2));
        studentInputPanel.add(new JLabel("Student Name:"));
        studentNameField = new JTextField();
        studentInputPanel.add(studentNameField);

        studentInputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        studentInputPanel.add(studentIdField);

        JButton addStudentButton = new JButton("Add Student");
        studentInputPanel.add(addStudentButton);

        // Student list view
        studentTextArea = new JTextArea();
        studentTextArea.setEditable(false);
        JScrollPane studentScrollPane = new JScrollPane(studentTextArea);

        studentPanel.add(studentInputPanel, BorderLayout.NORTH);
        studentPanel.add(studentScrollPane, BorderLayout.CENTER);

        // Action for Add Student button
        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        return studentPanel;
    }

    private void addStudent() {
        String name = studentNameField.getText();
        String id = studentIdField.getText();

        Student student = new Student(name, id);
        studentList.add(student);

        // Clear input fields
        studentNameField.setText("");
        studentIdField.setText("");

        updateStudentList();
    }

    private void updateStudentList() {
        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student.toString()).append("\n-----------------------\n");
        }
        studentTextArea.setText(sb.toString());
    }

    private JPanel createCoursePanel() {
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BorderLayout());

        // Input panel for course information
        JPanel courseInputPanel = new JPanel(new GridLayout(6, 2));
        courseInputPanel.add(new JLabel("Course Code:"));
        courseCodeField = new JTextField();
        courseInputPanel.add(courseCodeField);

        courseInputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        courseInputPanel.add(descriptionField);

        courseInputPanel.add(new JLabel("Prerequisites:"));
        prerequisitesField = new JTextField();
        courseInputPanel.add(prerequisitesField);

        courseInputPanel.add(new JLabel("Syllabus:"));
        syllabusField = new JTextField();
        courseInputPanel.add(syllabusField);

        courseInputPanel.add(new JLabel("Grading Scale:"));
        gradingScaleField = new JTextField();
        courseInputPanel.add(gradingScaleField);

        JButton addCourseButton = new JButton("Add Course");
        courseInputPanel.add(addCourseButton);

        // Course list view
        courseTextArea = new JTextArea();
        courseTextArea.setEditable(false);
        JScrollPane courseScrollPane = new JScrollPane(courseTextArea);

        coursePanel.add(courseInputPanel, BorderLayout.NORTH);
        coursePanel.add(courseScrollPane, BorderLayout.CENTER);

        // Action for Add Course button
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        return coursePanel;
    }

    private void addCourse() {
        String courseCode = courseCodeField.getText();
        String description = descriptionField.getText();
        String prerequisites = prerequisitesField.getText();
        String syllabus = syllabusField.getText();
        String gradingScale = gradingScaleField.getText();

        Course course = new Course(courseCode, description, prerequisites, syllabus, gradingScale);
        courseList.add(course);

        // Clear input fields
        courseCodeField.setText("");
        descriptionField.setText("");
        prerequisitesField.setText("");
        syllabusField.setText("");
        gradingScaleField.setText("");

        updateCourseList();
    }

    private void updateCourseList() {
        StringBuilder sb = new StringBuilder();
        for (Course course : courseList) {
            sb.append(course.toString()).append("\n-----------------------\n");
        }
        courseTextArea.setText(sb.toString());
    }

    private JPanel createAttendancePanel() {
        JPanel attendancePanel = new JPanel();
        attendancePanel.setLayout(new BorderLayout());

        // Attendance input fields for date and student name
        JPanel attendanceInputPanel = new JPanel(new GridLayout(3, 2));
        attendanceInputPanel.add(new JLabel("Student Name:"));
        attendanceStudentField = new JTextField();
        attendanceInputPanel.add(attendanceStudentField);

        attendanceInputPanel.add(new JLabel("Attendance Date (yyyy-mm-dd):"));
        attendanceDateField = new JTextField();
        attendanceInputPanel.add(attendanceDateField);

        JButton markAttendanceButton = new JButton("Mark Attendance");
        attendanceInputPanel.add(markAttendanceButton);

        // Attendance list view
        attendanceTextArea = new JTextArea();
        attendanceTextArea.setEditable(false);
        JScrollPane attendanceScrollPane = new JScrollPane(attendanceTextArea);

        attendancePanel.add(attendanceInputPanel, BorderLayout.NORTH);
        attendancePanel.add(attendanceScrollPane, BorderLayout.CENTER);

        // Action for Mark Attendance button
        markAttendanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });

        return attendancePanel;
    }

    private void markAttendance() {
        String studentName = attendanceStudentField.getText();
        String date = attendanceDateField.getText();

        // Find student by name
        Student student = findStudentByName(studentName);
        if (student != null) {
            student.addAttendance(date, true);  // Here we're assuming the student is present; this can be extended
            JOptionPane.showMessageDialog(this, "Attendance marked for " + studentName);
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.");
        }

        updateAttendanceList();
    }

    private void updateAttendanceList() {
        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student.name + "'s Attendance:\n");
            for (Map.Entry<String, Boolean> entry : student.getAttendanceRecord().entrySet()) {
                sb.append("Date: " + entry.getKey() + ", Present: " + entry.getValue() + "\n");
            }
            sb.append("-----------------------\n");
        }
        attendanceTextArea.setText(sb.toString());
    }

    private JPanel createMessagingPanel() {
        JPanel messagingPanel = new JPanel();
        messagingPanel.setLayout(new BorderLayout());

        // Input fields for sender, recipient, and message content
        JPanel messageInputPanel = new JPanel(new GridLayout(4, 2));
        messageInputPanel.add(new JLabel("Sender:"));
        senderField = new JTextField();
        messageInputPanel.add(senderField);

        messageInputPanel.add(new JLabel("Recipient:"));
        recipientField = new JTextField();
        messageInputPanel.add(recipientField);

        messageInputPanel.add(new JLabel("Message Content:"));
        messageContentField = new JTextField();
        messageInputPanel.add(messageContentField);

        JButton sendMessageButton = new JButton("Send Message");
        messageInputPanel.add(sendMessageButton);

        // Message list view
        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);

        messagingPanel.add(messageInputPanel, BorderLayout.NORTH);
        messagingPanel.add(messageScrollPane, BorderLayout.CENTER);

        // Action for Send Message button
        sendMessageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        return messagingPanel;
    }

    private void sendMessage() {
        String sender = senderField.getText();
        String recipient = recipientField.getText();
        String content = messageContentField.getText();

        Message message = new Message(sender, recipient, content);
        messageList.add(message);

        // Clear input fields
        senderField.setText("");
        recipientField.setText("");
        messageContentField.setText("");

        updateMessageList();
    }

    private void updateMessageList() {
        StringBuilder sb = new StringBuilder();
        for (Message message : messageList) {
            sb.append(message.toString()).append("\n-----------------------\n");
        }
        messageTextArea.setText(sb.toString());
    }

    private JPanel createAnnouncementPanel() {
        JPanel announcementPanel = new JPanel();
        announcementPanel.setLayout(new BorderLayout());

        // Input fields for announcement title and content
        JPanel announcementInputPanel = new JPanel(new GridLayout(3, 2));
        announcementInputPanel.add(new JLabel("Title:"));
        announcementTitleField = new JTextField();
        announcementInputPanel.add(announcementTitleField);

        announcementInputPanel.add(new JLabel("Content:"));
        announcementContentField = new JTextField();
        announcementInputPanel.add(announcementContentField);

        JButton postAnnouncementButton = new JButton("Post Announcement");
        announcementInputPanel.add(postAnnouncementButton);

        // Announcement list view
        announcementTextArea = new JTextArea();
        announcementTextArea.setEditable(false);
        JScrollPane announcementScrollPane = new JScrollPane(announcementTextArea);

        announcementPanel.add(announcementInputPanel, BorderLayout.NORTH);
        announcementPanel.add(announcementScrollPane, BorderLayout.CENTER);

        // Action for Post Announcement button
        postAnnouncementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                postAnnouncement();
            }
        });

        return announcementPanel;
    }

    private void postAnnouncement() {
        String title = announcementTitleField.getText();
        String content = announcementContentField.getText();

        Announcement announcement = new Announcement(title, content);
        announcementList.add(announcement);

        // Clear input fields
        announcementTitleField.setText("");
        announcementContentField.setText("");

        updateAnnouncementList();
    }

    private void updateAnnouncementList() {
        StringBuilder sb = new StringBuilder();
        for (Announcement announcement : announcementList) {
            sb.append(announcement.toString()).append("\n-----------------------\n");
        }
        announcementTextArea.setText(sb.toString());
    }

    private Student findStudentByName(String name) {
        for (Student student : studentList) {
            if (student.name.equals(name)) {
                return student;
            }
        }
        return null;
    }
    private JPanel createGradingPanel() {
        JPanel gradingPanel = new JPanel(new BorderLayout());

        // Table to display marks
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
                "Subject", "Midterm", "Assignment", "Quiz", "Terminal"
        }, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Input panel for adding marks
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Marks"));

        JLabel subjectLabel = new JLabel("Subject:");
        JTextField subjectField = new JTextField();

        JLabel midtermLabel = new JLabel("Midterm Marks:");
        JTextField midtermField = new JTextField();

        JLabel assignmentLabel = new JLabel("Assignment Marks:");
        JTextField assignmentField = new JTextField();

        JLabel quizLabel = new JLabel("Quiz Marks:");
        JTextField quizField = new JTextField();

        JLabel terminalLabel = new JLabel("Terminal Marks:");
        JTextField terminalField = new JTextField();

        JButton addButton = new JButton("Add Marks");

        // Add components to the input panel
        inputPanel.add(subjectLabel);
        inputPanel.add(subjectField);
        inputPanel.add(midtermLabel);
        inputPanel.add(midtermField);
        inputPanel.add(assignmentLabel);
        inputPanel.add(assignmentField);
        inputPanel.add(quizLabel);
        inputPanel.add(quizField);
        inputPanel.add(terminalLabel);
        inputPanel.add(terminalField);
        inputPanel.add(new JLabel()); // Placeholder for alignment
        inputPanel.add(addButton);

        // Add panels to the grading panel
        gradingPanel.add(inputPanel, BorderLayout.NORTH);
        gradingPanel.add(scrollPane, BorderLayout.CENTER);

        // Button action for adding marks
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subject = subjectField.getText();
                String midterm = midtermField.getText();
                String assignment = assignmentField.getText();
                String quiz = quizField.getText();
                String terminal = terminalField.getText();

                // Validate input fields
                if (subject.isEmpty() || midterm.isEmpty() || assignment.isEmpty() ||
                        quiz.isEmpty() || terminal.isEmpty()) {
                    JOptionPane.showMessageDialog(gradingPanel, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        // Add data to the table
                        double midtermMarks = Double.parseDouble(midterm);
                        double assignmentMarks = Double.parseDouble(assignment);
                        double quizMarks = Double.parseDouble(quiz);
                        double terminalMarks = Double.parseDouble(terminal);

                        tableModel.addRow(new Object[]{subject, midtermMarks, assignmentMarks, quizMarks, terminalMarks});

                        // Clear input fields
                        subjectField.setText("");
                        midtermField.setText("");
                        assignmentField.setText("");
                        quizField.setText("");
                        terminalField.setText("");

                        JOptionPane.showMessageDialog(gradingPanel, "Marks added successfully!");

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(gradingPanel, "Please enter valid numbers for marks!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        return gradingPanel;
    }
    public static void main(String[] args) {
        new StudentHub();
    }
}

