
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.sql.ResultSet;
// import java.util.Scanner;

// public class RestaurantBillingFullApp {

//     // JDBC connection variables
//     private static final String JDBC_URL = "jdbc:mysql://localhost:3306/restaurent";
//     private static final String JDBC_USER = "root";
//     private static final String JDBC_PASSWORD = "Arzumysql@6446";

//     // Predefined dishes and their prices
//     private static final String[] DISHES = {"vada", "idly", "dosa", "parotta"};
//     private static final double[] PRICES = {5.00, 10.00, 30.00, 15.00};

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         boolean continueOrdering = true; // This flag controls the loop for ordering multiple times
//         double totalOrderAmount = 0; // Accumulates the total amount for the order

//         try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
//             connection.setAutoCommit(false); // Enable transaction management

//             // Insert a new order in the Orders table and get the generated order_id
//             int orderId = createNewOrder(connection);

//             while (continueOrdering) {
//                 // Display the menu with predefined dishes
//                 System.out.println("--------Menu-------:");
//                 for (int i = 0; i < DISHES.length; i++) {
//                     System.out.println((i + 1) + ". " + DISHES[i] + " - Rs " + PRICES[i]);
//                 }

//                 // Loop until the user enters a valid dish number
//                 int dishNumber = 0;
//                 while (true) {
//                     System.out.println("Enter the number of the dish you want to order:");
//                     dishNumber = scanner.nextInt();

//                     if (dishNumber >= 1 && dishNumber <= DISHES.length) {
//                         break; // Valid number entered, exit the loop
//                     } else {
//                         System.out.println("Invalid number. Please enter a number between 1 and " + DISHES.length);
//                     }
//                 }

//                 // Get the chosen dish and its price
//                 String chosenDish = DISHES[dishNumber - 1];
//                 double pricePerDish = PRICES[dishNumber - 1];

//                 // Ask the user for the quantity
//                 System.out.println("Enter quantity:");
//                 int quantity = scanner.nextInt();

//                 // Calculate the amount for this dish
//                 double amount = pricePerDish * quantity;
//                 totalOrderAmount += amount; // Add to the total order amount

//                 // Insert the dish into Order_Details
//                 saveOrderDetails(connection, orderId, chosenDish, quantity, amount);

//                 // Display the result to the user
//                 System.out.println("Dish: " + chosenDish + ", Quantity: " + quantity + ", Amount: Rs " + amount);

//                 // Ask if the user wants to place another order
//                 System.out.println("Do you want to add another order? (Enter 1 for Yes, 0 for No)");
//                 int userResponse = scanner.nextInt();

//                 if (userResponse == 0) {
//                     continueOrdering = false; // Exit the loop if the user says "No"
//                 }
//             }

//             // Update the total amount for the order in the Orders table
//             updateOrderTotalAmount(connection, orderId, totalOrderAmount);

//             // Commit the transaction
//             connection.commit();

//             System.out.println("Order saved successfully!");
//             System.out.println("Total Order Amount: Rs " + totalOrderAmount);
//             System.out.println("Thank you for using the Restaurant Billing System!");

//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to create a new order in the Orders table and return the generated order_id
//     private static int createNewOrder(Connection connection) throws SQLException {
//         String sql = "INSERT INTO Orders1 (order_date, total_amount) VALUES (NOW(), 0)";
//         try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//             statement.executeUpdate();

//             // Get the generated order_id
//             try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                 if (generatedKeys.next()) {
//                     return generatedKeys.getInt(1); // return the generated order_id
//                 } else {
//                     throw new SQLException("Creating order failed, no ID obtained.");
//                 }
//             }
//         }
//     }

//     // Method to save the order details in the Order_Details table
//     private static void saveOrderDetails(Connection connection, int orderId, String dishName, int quantity, double amount) throws SQLException {
//         String sql = "INSERT INTO Order_Details (order_id, dish_name, quantity, amount) VALUES (?, ?, ?, ?)";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setInt(1, orderId);
//             statement.setString(2, dishName);
//             statement.setInt(3, quantity);
//             statement.setDouble(4, amount);
//             statement.executeUpdate();
//         }
//     }

//     // Method to update the total amount for an order in the Orders table
//     private static void updateOrderTotalAmount(Connection connection, int orderId, double totalAmount) throws SQLException {
//         String sql = "UPDATE Orders1 SET total_amount = ? WHERE order_id = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setDouble(1, totalAmount);
//             statement.setInt(2, orderId);
//             statement.executeUpdate();
//         }
//     }
// }
