# Car Showroom Management System

A comprehensive Java Swing-based GUI application for managing car showrooms, employees, and inventory.

## Features

### 🏢 Showroom Management
- Add new showrooms with details (name, address, manager, employees, cars in stock)
- View all showrooms in a organized table format
- Track showroom statistics

### 👥 Employee Management
- Add new employees with complete details (name, age, department, showroom)
- Automatic UUID generation for employee IDs
- View all employees in a searchable table

### 🚗 Car Management
- Add new cars with comprehensive details (name, color, fuel type, price, type, transmission)
- Track car inventory across showrooms
- View all cars in an organized table format

### 📊 Dashboard
- Real-time statistics showing total showrooms, employees, and cars
- Overview of system activity
- Modern card-based design with color-coded statistics

## System Requirements

- Java 8 or higher
- Any operating system with Java support (Windows, macOS, Linux)

## How to Run

### Method 1: Using Command Line
1. Open terminal/command prompt
2. Navigate to the project directory
3. Compile all Java files:
   ```bash
   javac *.java
   ```
4. Run the GUI application:
   ```bash
   java ShowroomManagementGUI
   ```

### Method 2: Using IDE
1. Open the project in your preferred Java IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. Make sure all Java files are in the same directory
3. Run the `ShowroomManagementGUI` class

## File Structure

```
├── ShowroomManagementGUI.java  # Main GUI application
├── Main.java                   # Original console-based main class
├── Showroom.java              # Showroom entity class
├── Employees.java             # Employee entity class
├── Cars.java                  # Car entity class
└── README.md                  # This file
```

## GUI Features

### Modern Design
- Clean, professional interface with modern color scheme
- Responsive navigation with hover effects
- Card-based dashboard with statistics
- Organized table views for all data

### User Experience
- Intuitive navigation between different sections
- Modal dialogs for adding new entries
- Real-time data updates
- Error handling with user-friendly messages

### Data Management
- In-memory data storage using Java Collections
- Automatic table updates when data is added
- Form validation for numeric inputs
- Success/error notifications

## Usage Guide

1. **Dashboard**: View system overview and statistics
2. **Showroom Management**: 
   - Click "➕ Add New Showroom" to add a new showroom
   - Fill in all required fields
   - Click "Save" to add the showroom
3. **Employee Management**:
   - Click "➕ Add New Employee" to add a new employee
   - Employee ID is automatically generated
   - Associate employee with a showroom
4. **Car Management**:
   - Click "➕ Add New Car" to add a new car
   - Enter all car details including price
   - Click "Save" to add the car

## Technical Details

### Architecture
- **GUI Framework**: Java Swing
- **Layout Management**: BorderLayout, GridLayout, BoxLayout
- **Data Storage**: ArrayList for in-memory storage
- **Event Handling**: ActionListener, MouseAdapter

### Design Patterns
- **MVC Pattern**: Separation of data (Model), GUI (View), and event handling (Controller)
- **Observer Pattern**: Automatic UI updates when data changes

### Key Components
- `JFrame`: Main application window
- `JPanel`: Container for organizing components
- `JTable`: Display data in tabular format
- `JDialog`: Modal dialogs for data entry
- `CardLayout`: Navigation between different sections

## Future Enhancements

- Database integration for persistent storage
- Search and filter functionality
- Edit and delete operations
- Data export capabilities
- User authentication and roles
- Advanced reporting features

## Contributing

Feel free to contribute to this project by:
- Adding new features
- Improving the UI/UX
- Fixing bugs
- Adding documentation

## License

This project is open source and available under the MIT License.
