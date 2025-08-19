import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ShowroomManagementGUI extends JFrame {
    
    // Data storage
    private List<Showroom> showrooms = new ArrayList<>();
    private List<Employees> employees = new ArrayList<>();
    private List<Cars> cars = new ArrayList<>();
    
    // Main panels
    private JPanel mainPanel;
    private JPanel showroomPanel;
    private JPanel employeePanel;
    private JPanel carPanel;
    private JPanel dashboardPanel;
    
    // Current panel reference
    private JPanel currentPanel;
    
    public ShowroomManagementGUI() {
        setTitle("Car Showroom Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize components
        initializeComponents();
        setupLayout();
        
        // Show dashboard by default
        showDashboard();
    }
    
    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));
        
        // Create panels
        createDashboardPanel();
        createShowroomPanel();
        createEmployeePanel();
        createCarPanel();
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    private void setupLayout() {
        // Create header
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Create navigation
        JPanel navPanel = createNavigationPanel();
        mainPanel.add(navPanel, BorderLayout.WEST);
        
        // Create content area
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        contentPanel.add(dashboardPanel, "DASHBOARD");
        contentPanel.add(showroomPanel, "SHOWROOM");
        contentPanel.add(employeePanel, "EMPLOYEE");
        contentPanel.add(carPanel, "CAR");
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Store reference to content panel for card layout
        this.currentPanel = contentPanel;
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 73, 94));
        headerPanel.setPreferredSize(new Dimension(1200, 80));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("🏎️ Car Showroom Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        return headerPanel;
    }
    
    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(44, 62, 80));
        navPanel.setPreferredSize(new Dimension(250, 800));
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        
        // Navigation buttons
        JButton dashboardBtn = createNavButton("📊 Dashboard", "DASHBOARD");
        JButton showroomBtn = createNavButton("🏢 Manage Showrooms", "SHOWROOM");
        JButton employeeBtn = createNavButton("👥 Manage Employees", "EMPLOYEE");
        JButton carBtn = createNavButton("🚗 Manage Cars", "CAR");
        
        // Add buttons to navigation panel
        navPanel.add(Box.createVerticalStrut(30));
        navPanel.add(dashboardBtn);
        navPanel.add(Box.createVerticalStrut(15));
        navPanel.add(showroomBtn);
        navPanel.add(Box.createVerticalStrut(15));
        navPanel.add(employeeBtn);
        navPanel.add(Box.createVerticalStrut(15));
        navPanel.add(carBtn);
        
        return navPanel;
    }
    
    private JButton createNavButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(52, 73, 94));
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setMaximumSize(new Dimension(230, 50));
        button.setPreferredSize(new Dimension(230, 50));
        button.setFocusPainted(false);
        
        button.addActionListener(e -> {
            CardLayout cl = (CardLayout) currentPanel.getLayout();
            cl.show(currentPanel, cardName);
        });
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(41, 128, 185));
            }
            
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(52, 73, 94));
            }
        });
        
        return button;
    }
    
    private void createDashboardPanel() {
        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(Color.WHITE);
        
        // Dashboard title
        JLabel titleLabel = new JLabel("Dashboard Overview");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Stats panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        statsPanel.setBackground(Color.WHITE);
        
        // Showroom stats
        JPanel showroomStats = createStatCard("🏢 Showrooms", String.valueOf(showrooms.size()), new Color(52, 152, 219));
        
        // Employee stats
        JPanel employeeStats = createStatCard("👥 Employees", String.valueOf(employees.size()), new Color(46, 204, 113));
        
        // Car stats
        JPanel carStats = createStatCard("🚗 Cars", String.valueOf(cars.size()), new Color(155, 89, 182));
        
        statsPanel.add(showroomStats);
        statsPanel.add(employeeStats);
        statsPanel.add(carStats);
        
        // Recent activity panel
        JPanel activityPanel = createActivityPanel();
        
        dashboardPanel.add(titleLabel, BorderLayout.NORTH);
        dashboardPanel.add(statsPanel, BorderLayout.CENTER);
        dashboardPanel.add(activityPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        card.setMaximumSize(new Dimension(300, 150));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);
        
        return card;
    }
    
    private JPanel createActivityPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Recent Activity"));
        
        JTextArea activityArea = new JTextArea();
        activityArea.setEditable(false);
        activityArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        activityArea.setText("Welcome to Car Showroom Management System!\n\n" +
                           "• Use the navigation menu to manage showrooms, employees, and cars\n" +
                           "• Add new entries using the 'Add New' buttons\n" +
                           "• View all entries in the respective tables\n" +
                           "• System automatically tracks all your data");
        
        JScrollPane scrollPane = new JScrollPane(activityArea);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    private void createShowroomPanel() {
        showroomPanel = new JPanel(new BorderLayout());
        showroomPanel.setBackground(Color.WHITE);
        
        // Title and add button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Showroom Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton addButton = new JButton("➕ Add New Showroom");
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> showAddShowroomDialog());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(addButton, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Table
        String[] columns = {"Name", "Address", "Manager", "Employees", "Cars in Stock"};
        Object[][] data = {};
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        
        showroomPanel.add(headerPanel, BorderLayout.NORTH);
        showroomPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Store table reference for updates
        showroomPanel.putClientProperty("table", table);
    }
    
    private void createEmployeePanel() {
        employeePanel = new JPanel(new BorderLayout());
        employeePanel.setBackground(Color.WHITE);
        
        // Title and add button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Employee Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton addButton = new JButton("➕ Add New Employee");
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> showAddEmployeeDialog());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(addButton, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Table
        String[] columns = {"ID", "Name", "Age", "Department", "Showroom"};
        Object[][] data = {};
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        
        employeePanel.add(headerPanel, BorderLayout.NORTH);
        employeePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Store table reference for updates
        employeePanel.putClientProperty("table", table);
    }
    
    private void createCarPanel() {
        carPanel = new JPanel(new BorderLayout());
        carPanel.setBackground(Color.WHITE);
        
        // Title and add button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Car Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton addButton = new JButton("➕ Add New Car");
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(e -> showAddCarDialog());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(addButton, BorderLayout.EAST);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Table
        String[] columns = {"Name", "Color", "Fuel Type", "Price", "Type", "Transmission"};
        Object[][] data = {};
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        
        carPanel.add(headerPanel, BorderLayout.NORTH);
        carPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Store table reference for updates
        carPanel.putClientProperty("table", table);
    }
    
    private void showAddShowroomDialog() {
        JDialog dialog = new JDialog(this, "Add New Showroom", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField managerField = new JTextField();
        JTextField employeesField = new JTextField();
        JTextField carsField = new JTextField();
        
        formPanel.add(new JLabel("Showroom Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Manager Name:"));
        formPanel.add(managerField);
        formPanel.add(new JLabel("Total Employees:"));
        formPanel.add(employeesField);
        formPanel.add(new JLabel("Cars in Stock:"));
        formPanel.add(carsField);
        
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        saveButton.addActionListener(e -> {
            try {
                Showroom showroom = new Showroom();
                showroom.showroom_name = nameField.getText();
                showroom.showroom_address = addressField.getText();
                showroom.manager_name = managerField.getText();
                showroom.total_employees = Integer.parseInt(employeesField.getText());
                showroom.total_cars_in_stock = Integer.parseInt(carsField.getText());
                
                showrooms.add(showroom);
                updateShowroomTable();
                updateDashboard();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Showroom added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for employees and cars!");
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    private void showAddEmployeeDialog() {
        JDialog dialog = new JDialog(this, "Add New Employee", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField departmentField = new JTextField();
        JTextField showroomField = new JTextField();
        
        formPanel.add(new JLabel("Employee Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentField);
        formPanel.add(new JLabel("Showroom Name:"));
        formPanel.add(showroomField);
        
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        saveButton.addActionListener(e -> {
            try {
                Employees employee = new Employees();
                employee.emp_name = nameField.getText();
                employee.emp_age = Integer.parseInt(ageField.getText());
                employee.emp_department = departmentField.getText();
                employee.showroom_name = showroomField.getText();
                
                employees.add(employee);
                updateEmployeeTable();
                updateDashboard();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Employee added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid age!");
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    private void showAddCarDialog() {
        JDialog dialog = new JDialog(this, "Add New Car", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField nameField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField fuelField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField transmissionField = new JTextField();
        
        formPanel.add(new JLabel("Car Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Color:"));
        formPanel.add(colorField);
        formPanel.add(new JLabel("Fuel Type:"));
        formPanel.add(fuelField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Car Type:"));
        formPanel.add(typeField);
        formPanel.add(new JLabel("Transmission:"));
        formPanel.add(transmissionField);
        
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        saveButton.addActionListener(e -> {
            try {
                Cars car = new Cars();
                car.car_name = nameField.getText();
                car.car_color = colorField.getText();
                car.car_fuel_type = fuelField.getText();
                car.car_price = Integer.parseInt(priceField.getText());
                car.car_type = typeField.getText();
                car.car_transmission = transmissionField.getText();
                
                cars.add(car);
                updateCarTable();
                updateDashboard();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Car added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid price!");
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    private void updateShowroomTable() {
        JTable table = (JTable) showroomPanel.getClientProperty("table");
        String[] columns = {"Name", "Address", "Manager", "Employees", "Cars in Stock"};
        Object[][] data = new Object[showrooms.size()][5];
        
        for (int i = 0; i < showrooms.size(); i++) {
            Showroom s = showrooms.get(i);
            data[i][0] = s.showroom_name;
            data[i][1] = s.showroom_address;
            data[i][2] = s.manager_name;
            data[i][3] = s.total_employees;
            data[i][4] = s.total_cars_in_stock;
        }
        
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }
    
    private void updateEmployeeTable() {
        JTable table = (JTable) employeePanel.getClientProperty("table");
        String[] columns = {"ID", "Name", "Age", "Department", "Showroom"};
        Object[][] data = new Object[employees.size()][5];
        
        for (int i = 0; i < employees.size(); i++) {
            Employees e = employees.get(i);
            data[i][0] = e.emp_id;
            data[i][1] = e.emp_name;
            data[i][2] = e.emp_age;
            data[i][3] = e.emp_department;
            data[i][4] = e.showroom_name;
        }
        
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }
    
    private void updateCarTable() {
        JTable table = (JTable) carPanel.getClientProperty("table");
        String[] columns = {"Name", "Color", "Fuel Type", "Price", "Type", "Transmission"};
        Object[][] data = new Object[cars.size()][6];
        
        for (int i = 0; i < cars.size(); i++) {
            Cars c = cars.get(i);
            data[i][0] = c.car_name;
            data[i][1] = c.car_color;
            data[i][2] = c.car_fuel_type;
            data[i][3] = c.car_price;
            data[i][4] = c.car_type;
            data[i][5] = c.car_transmission;
        }
        
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }
    
    private void updateDashboard() {
        // Update dashboard stats
        JPanel statsPanel = (JPanel) dashboardPanel.getComponent(1);
        JPanel showroomStats = (JPanel) statsPanel.getComponent(0);
        JPanel employeeStats = (JPanel) statsPanel.getComponent(1);
        JPanel carStats = (JPanel) statsPanel.getComponent(2);
        
        JLabel showroomCount = (JLabel) showroomStats.getComponent(2);
        JLabel employeeCount = (JLabel) employeeStats.getComponent(2);
        JLabel carCount = (JLabel) carStats.getComponent(2);
        
        showroomCount.setText(String.valueOf(showrooms.size()));
        employeeCount.setText(String.valueOf(employees.size()));
        carCount.setText(String.valueOf(cars.size()));
    }
    
    private void showDashboard() {
        CardLayout cl = (CardLayout) currentPanel.getLayout();
        cl.show(currentPanel, "DASHBOARD");
    }
    
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        // Create and show GUI
        SwingUtilities.invokeLater(() -> {
            ShowroomManagementGUI gui = new ShowroomManagementGUI();
            gui.setVisible(true);
        });
    }
}
