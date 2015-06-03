import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.*;

public class orders{
	
	static Connection conn=null;
	
	static UtilDateModel model;
	static UtilDateModel model2;
	
	JFrame addUser;
	JFrame addOrder;
	JFrame addProduct;
	JFrame addItem;
	
	JButton ok;
	JButton cancel;
	JButton newOrderButton;
	JButton newProductButton;
	JButton newUserButton;
	JButton addProductToOrder;
	
	static JTextField prodNameField;
	static JTextField descriptionField;
    static JTextField priceField;
	 
	JLabel prodName;
	JLabel description;
	JLabel price;
	 
	JLabel user;
	JLabel f_name;
	JLabel l_name;
	JLabel dob;
	
	JLabel accID;
	JLabel date;
	JLabel billingID;
	JLabel shippingID;
	 
	static JTextField userText;
	static JTextField fNameText;
	static JTextField lNameText;
	static JTextField dobText;
	
	static JTextField accIDField;
	static JTextField dateField;
	static JTextField shippingField;
	static JTextField billingField;
	
	static JTable table;
	static JTable table2;
	static JTable table3;
	static JTable table4;
	
	static int newUserYear;
	static int newUserMonth;
	static int newUserDay;
	 
	static DefaultTableModel modelTable;
	static DefaultTableModel modelTable2;
	static DefaultTableModel modelTable3;
	static DefaultTableModel modelTable4;
	
	static JTextField orderIDField;
	static JTextField productIDField;
	static JTextField quantityField;
	
	static JLabel orderID;
	static JLabel productID;
	static JLabel quantity;
	
	public static void Delete(int row, int column,JTable target) throws SQLException {
		 
		 System.out.println(target.getModel().getColumnName(column));
		 
		 if(target.getModel().getColumnName(column).equals("")) {
			 if(table.isShowing()) {
				 if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?","Delete User",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					  String sql = "delete from userlist where accountid = " + table.getValueAt(row, 0);
				      
				      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
				      conn.createStatement().executeUpdate(sql);
				      
				      modelTable.removeRow(row);
				 }
			 }
			 if(table2.isShowing()) {
				 if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this order?","Delete Order",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					 String sql = "delete from orderitems where orderid = " + table2.getValueAt(row, 0);

				      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
				      conn.createStatement().executeUpdate(sql);
				      
				      sql = "delete from orders where orderid = " + table2.getValueAt(row, 0);
				      conn.createStatement().executeUpdate(sql);
				      
				      modelTable2.removeRow(row);
				 }
			 }
			 if(table3.isShowing()) {
				 if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?","Delete Product",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					 String sql = "delete from orderitems where productid =" + table3.getValueAt(row, 0 );
					 
					 conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
				     conn.createStatement().executeUpdate(sql);
					 
					 sql = "delete from products where productid = " + table3.getValueAt(row, 0);
					
				     conn.createStatement().executeUpdate(sql);
				     
				     int i = 0;
				     
				     while(i<table4.getRowCount()) {
				    	 if(Integer.parseInt(table4.getValueAt(i, 1).toString())==Integer.parseInt(table3.getValueAt(row, 0).toString())) {
				    		modelTable4.removeRow(i);
				    	 }
				    	
				    	 i++;
				     }
				      
				     modelTable3.removeRow(row);
				 }
			 }
			 if(table4.isShowing()) {
				 if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete an item from this order?","Delete Item from Order",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					 
					 String sql = "delete from orderitems where productid =" + table4.getValueAt(row, 1 ) + " and orderid = " + table4.getValueAt(row, 0 );
					 
					 conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
				     conn.createStatement().executeUpdate(sql);
					 
				      
				      modelTable4.removeRow(row);
				 }
			 }
		 }
     }
	 
	public orders() throws SQLException {
		 newUserButton = new JButton("New user");
		 newProductButton = new JButton("New product");
		 newOrderButton= new JButton("New order");
		 addProductToOrder = new JButton("Add products to order");
		 
		 JFrame frame = new JFrame();	
		 frame.setSize(600, 325);
		 frame.setTitle("Online Store Database");    
		 frame.setResizable(false);
		 frame.setLayout(new GridBagLayout());
		 
		 GridBagConstraints c = new GridBagConstraints();
		    
		 Object rowData[][] = { };
		 Object columnNames[] = {"ID Number", "First Name", "Last Name", "DOB","Username",""};
	     Object columnNames2[] = {"OrderID","AccountID","Date","ShippingAddressID","BillingAddressID",""};
		 Object columnNames3[] = {"ProductID","ProductName","Description","Price",""};
		 Object columnNames4[] = {"OrderID","ProductID","Quantity",""};		    
			    
	     table = new JTable(new DefaultTableModel(rowData, columnNames));
		 table2 = new JTable(new DefaultTableModel(rowData, columnNames2));
	     table3 = new JTable(new DefaultTableModel(rowData, columnNames3));
 	     table4 = new JTable(new DefaultTableModel(rowData, columnNames4));
			    
  	     modelTable = (DefaultTableModel) table.getModel();
	     modelTable2 = (DefaultTableModel)table2.getModel(); 
	     modelTable3 = (DefaultTableModel)table3.getModel();
	     modelTable4 = (DefaultTableModel)table4.getModel();
			    
	     conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
			    
	     ResultSet rs = conn.createStatement().executeQuery("select * from userlist");
	     ResultSet rs2 = conn.createStatement().executeQuery("select * from orders");
	     ResultSet rs3 = conn.createStatement().executeQuery("select * from products");
  	     ResultSet rs4 = conn.createStatement().executeQuery("select * from orderitems");
			    
  	     while(rs.next()) {
  	    	 modelTable.addRow(new Object[]{rs.getInt("AccountID"), rs.getString("F_Name"), rs.getString("L_Name"),rs.getString("DOB"),rs.getString("Username"),"Delete"});
  	     }
	     while(rs2.next()) {
			 modelTable2.addRow(new Object[]{rs2.getInt("OrderID"), rs2.getInt("AccountID"), rs2.getString("DateAndTime"),rs2.getInt("ShippingAddressID"),rs2.getInt("BillingAddressID"),"Delete"});
	     }
	     while(rs3.next()) {
			 modelTable3.addRow(new Object[]{rs3.getInt("ProductID"), rs3.getString("ProductName"), rs3.getString("Description"),rs3.getDouble("Price"),"Delete"});
	     }
	     while(rs4.next()) {
			 modelTable4.addRow(new Object[]{rs4.getInt("OrderID"), rs4.getInt("ProductID"), rs4.getInt("Quantity"),"Delete"});
	     }
			        
	     table.getColumnModel().getColumn(5).setPreferredWidth(50);
	     table2.getColumnModel().getColumn(5).setPreferredWidth(50);
	     table2.getColumnModel().getColumn(0).setPreferredWidth(27);
	     table2.getColumnModel().getColumn(1).setPreferredWidth(35);
			    
	     table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	     table.setPreferredScrollableViewportSize(new Dimension(550,200));
	     table.setFillsViewportHeight(true);
			
	     table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	     table2.setPreferredScrollableViewportSize(new Dimension(550,200));
	     table2.setFillsViewportHeight(true);
	     
	     table3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	     table3.setPreferredScrollableViewportSize(new Dimension(550,200));
	     table3.setFillsViewportHeight(true);
			    
	     table4.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	     table4.setPreferredScrollableViewportSize(new Dimension(550,200));
	     table4.setFillsViewportHeight(true);

	     table.getColumn("").setCellRenderer(new ButtonRenderer());
	     table2.getColumn("").setCellRenderer(new ButtonRenderer());	    
	     table3.getColumn("").setCellRenderer(new ButtonRenderer());
	     table4.getColumn("").setCellRenderer(new ButtonRenderer());
	     
	     final JScrollPane scrollPane = new JScrollPane(table);
	     final JScrollPane scrollPane2 = new JScrollPane(table2);   
	  	 final JScrollPane scrollPane3 = new JScrollPane(table3);
	  	 final JScrollPane scrollPane4 = new JScrollPane(table4);		
		    
		 JTabbedPane tabbedPane = new JTabbedPane();
		          
		 JPanel users = new JPanel();
		 users.add(scrollPane);
  
		 JPanel orders = new JPanel();
		 orders.add(scrollPane2);
	        
		 JPanel products = new JPanel();
		 products.add(scrollPane3);
		    
		 JPanel itemOrders = new JPanel();
		 itemOrders.add(scrollPane4);
		    
	     tabbedPane.addTab("Users", users);
	     tabbedPane.addTab("Orders", orders);
	     tabbedPane.addTab("Products", products);
	     tabbedPane.addTab("Order Contents", itemOrders);
		    
	     c.weightx = 1;
		 c.fill = GridBagConstraints.HORIZONTAL;
		 c.gridx = 0;
		 c.gridy = 0;
		 c.gridwidth=5;
		 frame.add(tabbedPane,c);
    
		 c.gridx=1;
         c.gridy=1;
		 c.insets=new Insets(2, 2, 2, 2);
         c.weightx=.5;
		 c.anchor=GridBagConstraints.PAGE_END;
		 c.weighty = 1.0;
		 c.gridwidth=1;   
		 frame.add(newUserButton, c);
		    
		 c.gridx=2;
         c.gridy=1;
		 c.insets=new Insets(2, 2, 2, 2);
	     c.weightx=.5;
	     c.anchor=GridBagConstraints.PAGE_END;
		 c.weighty = 1.0;
		 c.gridwidth=1;
		 frame.add(newOrderButton,c);
		    
		 c.gridx=3;
	     c.gridy=1;
		 c.insets=new Insets(2, 2, 2, 2);
	     c.weightx=.5;
		 c.anchor=GridBagConstraints.PAGE_END;
		 c.weighty = 1.0;
		 c.gridwidth=1;
		 frame.add(newProductButton,c);
		    
	     c.gridx=4;
       	 c.gridy=1;
	     c.insets=new Insets(2, 2, 2, 2);
      	 c.weightx=.5;
	     c.anchor=GridBagConstraints.PAGE_END;
		 c.weighty = 1.0;
		 c.gridwidth=1;
		 frame.add(addProductToOrder,c);
	
		 newUserClick n =new newUserClick();
		 newUserButton.addActionListener(n);
				 
		 newOrderClick o =new newOrderClick();
		 newOrderButton.addActionListener(o);
				 
		 newProductClick p =new newProductClick();
		 newProductButton.addActionListener(p);
				 
		 newItemAdd r =new newItemAdd();
		 addProductToOrder.addActionListener(r);
		
		 frame.setVisible(true);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 
		 table.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 1) {
					 JTable target = (JTable) e.getSource();
				     int row = target.getSelectedRow();
				     int column = target.getSelectedColumn();
				              
				     try {
				    	 Delete(row, column, target);
					 } 
				     catch (SQLException e1) {
									e1.printStackTrace();
					 }
				 }
			 }
		 });
		
		 table2.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 1) {
					 JTable target = (JTable) e.getSource();
				     int row = target.getSelectedRow();
				     int column = target.getSelectedColumn();
				              
				     try {
				    	 Delete(row, column, target);
					 } 
				     catch (SQLException e1) {
									e1.printStackTrace();
					 }
				 }
			 }
		 });
				 
		 table3.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 1) {
					 JTable target = (JTable) e.getSource();
				     int row = target.getSelectedRow();
				     int column = target.getSelectedColumn();
				              
				     try {
				    	 Delete(row, column, target);
					 } 
				     catch (SQLException e1) {
									e1.printStackTrace();
					 }
				 }
			 }
		 });
				 
		 table4.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 1) {
					 JTable target = (JTable) e.getSource();
				     int row = target.getSelectedRow();
				     int column = target.getSelectedColumn();
				              
				     try {
				    	 Delete(row, column, target);
					 } 
				     catch (SQLException e1) {
									e1.printStackTrace();
					 }
				 }
			 }
		 });		
	}
	 
	public void addUserFrame() {
		 SpringLayout layout = new SpringLayout();
		 
		 addUser = new JFrame();
		 addUser.setSize(320, 180);
		 addUser.setTitle("New User");
		 addUser.setResizable(false);
		 addUser.setLayout(layout);
		 
		 model = new UtilDateModel();
		 model.setDate(1990, 8, 24);
		 
		 Properties p = new Properties();
		 p.put("text.today", "Today");
		 p.put("text.month", "Month");
		 p.put("text.year", "Year");
		 
		 JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		 JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		 
		 userText = new JTextField(15);
		 fNameText = new JTextField(15);
		 lNameText = new JTextField(15);
		 dobText = new JTextField(15); //Date of Birth
		 
		 user = new JLabel("Username:");
		 f_name = new JLabel("First Name:");
		 l_name = new JLabel("Last Name:");
		 dob = new JLabel("Date of Birth:");
		 ok = new JButton("OK");
		 cancel = new JButton("Cancel");
		 
		 layout.putConstraint(SpringLayout.WEST, user, 10, SpringLayout.WEST, addUser);
		 layout.putConstraint(SpringLayout.NORTH, user, 10, SpringLayout.NORTH, addUser);
		 addUser.add(user);
		 
		 layout.putConstraint(SpringLayout.WEST, userText, 19, SpringLayout.EAST, user);
		 layout.putConstraint(SpringLayout.NORTH, userText, 10, SpringLayout.NORTH, addUser);
		 addUser.add(userText);
		 
		 layout.putConstraint(SpringLayout.WEST, f_name, 10, SpringLayout.WEST, addUser);
		 layout.putConstraint(SpringLayout.NORTH, f_name, 35, SpringLayout.NORTH, addUser);
		 addUser.add(f_name);
		 
		 layout.putConstraint(SpringLayout.WEST,fNameText , 19, SpringLayout.EAST, user);
		 layout.putConstraint(SpringLayout.NORTH, fNameText, 35, SpringLayout.NORTH, addUser);
		 addUser.add(fNameText);
		 
		 layout.putConstraint(SpringLayout.WEST, l_name, 10, SpringLayout.WEST, addUser);
		 layout.putConstraint(SpringLayout.NORTH, l_name, 60, SpringLayout.NORTH, addUser);
		 addUser.add(l_name);
		 
		 layout.putConstraint(SpringLayout.WEST, lNameText, 19, SpringLayout.EAST, user);
		 layout.putConstraint(SpringLayout.NORTH, lNameText, 60, SpringLayout.NORTH, addUser);
		 addUser.add(lNameText);
		 
		 layout.putConstraint(SpringLayout.WEST, dob, 10, SpringLayout.WEST, addUser);
		 layout.putConstraint(SpringLayout.NORTH, dob, 85, SpringLayout.NORTH, addUser);
		 addUser.add(dob);
		 
		 layout.putConstraint(SpringLayout.WEST,datePicker , 19, SpringLayout.EAST, user);
		 layout.putConstraint(SpringLayout.NORTH, datePicker, 85, SpringLayout.NORTH, addUser);
		 addUser.add(datePicker);
		 
		 layout.putConstraint(SpringLayout.WEST,ok , 80, SpringLayout.EAST, addUser);
		 layout.putConstraint(SpringLayout.NORTH, ok, 119, SpringLayout.NORTH, addUser);
		 addUser.add(ok);		
		 
		 layout.putConstraint(SpringLayout.WEST,cancel , 8, SpringLayout.EAST, ok);
		 layout.putConstraint(SpringLayout.NORTH, cancel, 119, SpringLayout.NORTH, addUser);
		 addUser.add(cancel);		
		 
		 addUser.setVisible(true);
		 
		 okClick o=new okClick();
		 ok.addActionListener(o);
		 
		 cancelClick c= new cancelClick();
		 cancel.addActionListener(c);
	 }
	
	public void addOrderFrame() {
		 SpringLayout layout = new SpringLayout();
		 
		 addOrder = new JFrame();
		 addOrder.setSize(350, 180);
		 addOrder.setTitle("New Order");
		 addOrder.setResizable(false);
		 addOrder.setLayout(layout);

		 accIDField = new JTextField(15);
		 dateField = new JTextField(15);
		 shippingField = new JTextField(15);
		 billingField = new JTextField(15);
		 
		 accID = new JLabel("Account ID: ");
		 date = new JLabel("Date: ");
		 shippingID = new JLabel("Shipping address ID: ");
		 billingID = new JLabel("Billing address ID: ");
		 ok = new JButton("OK");
		 cancel = new JButton("Cancel");
		 
		 model = new UtilDateModel();
		 model.setDate(1990, 8, 24);
		 
		 Properties p = new Properties();
		 p.put("text.today", "Today");
		 p.put("text.month", "Month");
		 p.put("text.year", "Year");
		 
		 JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		 JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		
		 layout.putConstraint(SpringLayout.WEST, accID, 10, SpringLayout.WEST, addOrder);
		 layout.putConstraint(SpringLayout.NORTH, accID, 10, SpringLayout.NORTH, addOrder);
		 addOrder.add(accID);
		 
		 layout.putConstraint(SpringLayout.WEST, accIDField, 19, SpringLayout.EAST, billingID);
		 layout.putConstraint(SpringLayout.NORTH, accIDField, 10, SpringLayout.NORTH, addOrder);
		 addOrder.add(accIDField);
		 
		 layout.putConstraint(SpringLayout.WEST, date, 10, SpringLayout.WEST, addOrder);
		 layout.putConstraint(SpringLayout.NORTH, date, 35, SpringLayout.NORTH, addOrder);
		 addOrder.add(date);
		 
		 layout.putConstraint(SpringLayout.WEST, billingID, 10, SpringLayout.WEST, addOrder);
		 layout.putConstraint(SpringLayout.NORTH,billingID, 60, SpringLayout.NORTH, addOrder);
		 addOrder.add(billingID);
		 
		 layout.putConstraint(SpringLayout.WEST, billingField, 19, SpringLayout.EAST, billingID);
		 layout.putConstraint(SpringLayout.NORTH, billingField, 60, SpringLayout.NORTH, addOrder);
		 addOrder.add(billingField);
		 
		 layout.putConstraint(SpringLayout.WEST, shippingID, 10, SpringLayout.WEST, addOrder);
		 layout.putConstraint(SpringLayout.NORTH, shippingID, 85, SpringLayout.NORTH, addOrder);
		 addOrder.add(shippingID);
		 
		 layout.putConstraint(SpringLayout.WEST, shippingField, 19, SpringLayout.EAST, billingID);
		 layout.putConstraint(SpringLayout.NORTH, shippingField, 85, SpringLayout.NORTH, addOrder);
		 addOrder.add(shippingField);
		 
		 layout.putConstraint(SpringLayout.WEST,ok , 80, SpringLayout.EAST, addOrder);
		 layout.putConstraint(SpringLayout.NORTH, ok, 119, SpringLayout.NORTH, addOrder);
		 addOrder.add(ok);		
		 
		 layout.putConstraint(SpringLayout.WEST,cancel , 8, SpringLayout.EAST, ok);
		 layout.putConstraint(SpringLayout.NORTH, cancel, 119, SpringLayout.NORTH, addOrder);
		 addOrder.add(cancel);	
		 
		 layout.putConstraint(SpringLayout.WEST,datePicker , 19, SpringLayout.EAST, billingID);
		 layout.putConstraint(SpringLayout.NORTH, datePicker, 32, SpringLayout.NORTH, addOrder);
		 addOrder.add(datePicker);

		 addOrder.setVisible(true);
		 
		 okClick o=new okClick();
		 cancelClick c= new cancelClick();
		 
		 ok.addActionListener(o);
		 cancel.addActionListener(c);	
	 }
	 
	public void addProductFrame() {
		 SpringLayout layout = new SpringLayout();
		 
		 addProduct = new JFrame();
		 addProduct.setSize(320, 150);
		 addProduct.setTitle("New Product");
		 addProduct.setResizable(false);
		 addProduct.setLayout(layout);
		 
		 prodNameField = new JTextField(15);
		 descriptionField = new JTextField(15);
		 priceField = new JTextField(15);
		
		 prodName = new JLabel("Product Name: ");
		 description = new JLabel("Description: ");
		 price = new JLabel("Price: ");
		 ok = new JButton("OK");
		 cancel = new JButton("Cancel");
		 
		 layout.putConstraint(SpringLayout.WEST, prodName, 10, SpringLayout.WEST, addProduct);
		 layout.putConstraint(SpringLayout.NORTH, prodName, 10, SpringLayout.NORTH, addProduct);
		 addProduct.add(prodName);
		 
		 layout.putConstraint(SpringLayout.WEST, prodNameField, 19, SpringLayout.EAST, prodName);
		 layout.putConstraint(SpringLayout.NORTH, prodNameField, 10, SpringLayout.NORTH, addProduct);
		 addProduct.add(prodNameField);
		 
		 layout.putConstraint(SpringLayout.WEST, description, 10, SpringLayout.WEST, addProduct);
		 layout.putConstraint(SpringLayout.NORTH, description, 35, SpringLayout.NORTH, addProduct);
		 addProduct.add(description);
		 
		 layout.putConstraint(SpringLayout.WEST, descriptionField, 19, SpringLayout.EAST, prodName);
		 layout.putConstraint(SpringLayout.NORTH, descriptionField, 35, SpringLayout.NORTH, addProduct);
		 addProduct.add(descriptionField);
		 
		 layout.putConstraint(SpringLayout.WEST, price, 10, SpringLayout.WEST, addProduct);
		 layout.putConstraint(SpringLayout.NORTH, price, 60, SpringLayout.NORTH, addProduct);
		 addProduct.add(price);
		 
		 layout.putConstraint(SpringLayout.WEST, priceField, 19, SpringLayout.EAST, prodName);
		 layout.putConstraint(SpringLayout.NORTH, priceField, 60, SpringLayout.NORTH, addProduct);
		 addProduct.add(priceField);
		 
		 layout.putConstraint(SpringLayout.WEST,ok , 80, SpringLayout.EAST, addProduct);
		 layout.putConstraint(SpringLayout.NORTH, ok, 90, SpringLayout.NORTH, addProduct);
		 addProduct.add(ok);		
		 
		 layout.putConstraint(SpringLayout.WEST,cancel , 8, SpringLayout.EAST, ok);
		 layout.putConstraint(SpringLayout.NORTH, cancel, 90, SpringLayout.NORTH, addProduct);
		 addProduct.add(cancel);	
		 
		 addProduct.setVisible(true);
		 
		 okClick o=new okClick();
		 ok.addActionListener(o);
		 
		 cancelClick c= new cancelClick();
		 cancel.addActionListener(c);
	 }
	 
	public void addItemFrame() {
		 SpringLayout layout = new SpringLayout();
		 
		 addItem = new JFrame();
		 addItem.setSize(320, 150);
		 addItem.setTitle("Add Products to Order");
		 addItem.setResizable(false);
		 addItem.setLayout(layout);
		 
		 orderIDField = new JTextField(15);
		 productIDField = new JTextField(15);
		 quantityField = new JTextField(15);
	
		 orderID = new JLabel("Order ID:");
		 productID = new JLabel("Product ID:");
		 quantity = new JLabel("Quantity:");
		 ok = new JButton("OK");
		 cancel = new JButton("Cancel");
		 
		 layout.putConstraint(SpringLayout.WEST, orderID, 10, SpringLayout.WEST, addItem);
		 layout.putConstraint(SpringLayout.NORTH, orderID, 10, SpringLayout.NORTH, addItem);
		 addItem.add(orderID);
		 
		 layout.putConstraint(SpringLayout.WEST, orderIDField, 19, SpringLayout.EAST, productID);
		 layout.putConstraint(SpringLayout.NORTH, orderIDField, 10, SpringLayout.NORTH, addItem);
		 addItem.add(orderIDField);
		 
		 layout.putConstraint(SpringLayout.WEST, productID, 10, SpringLayout.WEST, addItem);
		 layout.putConstraint(SpringLayout.NORTH, productID, 35, SpringLayout.NORTH, addItem);
		 addItem.add(productID);
		 
		 layout.putConstraint(SpringLayout.WEST, productIDField, 19, SpringLayout.EAST, productID);
		 layout.putConstraint(SpringLayout.NORTH, productIDField, 35, SpringLayout.NORTH, addItem);
		 addItem.add(productIDField);
		 
		 layout.putConstraint(SpringLayout.WEST, quantity, 10, SpringLayout.WEST, addItem);
		 layout.putConstraint(SpringLayout.NORTH, quantity, 60, SpringLayout.NORTH, addItem);
		 addItem.add(quantity);
		 
		 layout.putConstraint(SpringLayout.WEST, quantityField, 19, SpringLayout.EAST, productID);
		 layout.putConstraint(SpringLayout.NORTH, quantityField, 60, SpringLayout.NORTH, addItem);
		 addItem.add(quantityField);
		 
		 layout.putConstraint(SpringLayout.WEST, ok, 80, SpringLayout.EAST, addItem);
		 layout.putConstraint(SpringLayout.NORTH, ok, 90, SpringLayout.NORTH, addItem);
		 addItem.add(ok);		
		 
		 layout.putConstraint(SpringLayout.WEST,cancel , 8, SpringLayout.EAST, ok);
		 layout.putConstraint(SpringLayout.NORTH, cancel, 90, SpringLayout.NORTH, addItem);
		 addItem.add(cancel);	
		 
		 okClick o=new okClick();
		 ok.addActionListener(o);
		 
		 cancelClick c= new cancelClick();
		 cancel.addActionListener(c);
		 
		 addItem.setVisible(true);
	 }
	 
	public static void addUser() throws SQLException, ClassNotFoundException {
		  String FirstName = fNameText.getText();
		  String LastName = lNameText.getText();
		  String DOB = dobText.getText();
		  String User = userText.getText();
		  
		  newUserYear = model.getYear();
		  newUserMonth = model.getMonth()+1;
		  newUserDay = model.getDay();
		  
		  String newUserYearS=Integer.toString(newUserYear); 
		  String newUserMonthS=Integer.toString(newUserMonth); 
		  String newUserDayS=Integer.toString(newUserDay); 
			 
		  if(Integer.toString(newUserDay).length()==1) {
			  newUserDayS="0"+ newUserDayS;
		  }
		  if(Integer.toString(newUserMonth).length()==1) {
			  newUserMonthS="0"+ newUserMonthS;
		  }
		  
		  DOB=newUserYearS+"-"+newUserMonthS+"-"+newUserDayS;
		  
	      String sql = "insert into userList values(null,'"+FirstName+"','"+LastName+"','"+DOB+"','"+User+"')";
	      
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
	      conn.createStatement().executeUpdate(sql);
	      
		  ResultSet rs = conn.createStatement().executeQuery("select * from userlist");
		    
		  int ID = 0;
		    
		  while(rs.next()) {
			  ID = rs.getInt("AccountID");
		  }
	      
	      modelTable.addRow(new Object[]{ID, FirstName, LastName,DOB,User,"Delete"});
	}
	
	public static void addOrder() throws SQLException, ClassNotFoundException {
		  String accountID = accIDField.getText();
		  String Date = "";
		  String shipAddress = shippingField.getText();
		  String billingAddress = billingField.getText();
		  
		  newUserYear = model.getYear();
		  newUserMonth = model.getMonth()+1;
		  newUserDay = model.getDay();
		  
		  String newUserYearS=Integer.toString(newUserYear); 
		  String newUserMonthS=Integer.toString(newUserMonth); 
		  String newUserDayS=Integer.toString(newUserDay); 
			 
		  if(Integer.toString(newUserDay).length()==1) {
			  newUserDayS="0"+ newUserDayS;
		  }
		  if(Integer.toString(newUserMonth).length()==1) {
			  newUserMonthS="0"+ newUserMonthS;
		  }
		  
		  Date = newUserYearS+"-"+newUserMonthS+"-"+newUserDayS;
		 
	      String sql = "insert into orders values(null,'"+accountID+"','"+Date+"','"+shipAddress+"','"+billingAddress+"')";
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
	      conn.createStatement().executeUpdate(sql);  
		  ResultSet rs = conn.createStatement().executeQuery("select * from orders");
		    
		  int ID = 0;
		    
		  while(rs.next()) {
			  ID = rs.getInt("OrderID");
		  }
	      
		  modelTable2.addRow(new Object[]{ID, accountID, Date,shipAddress,billingAddress,"Delete"});
	}
	
	public static void addProduct() throws SQLException, ClassNotFoundException {
		  String prodName = prodNameField.getText();
		  String Description = descriptionField.getText();
		  String price = priceField.getText();
		  String sql = "insert into products values(null,'"+prodName+"','"+Description+"','"+price+"')";
	      
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
	      conn.createStatement().executeUpdate(sql);
	      ResultSet rs = conn.createStatement().executeQuery("select * from products");
		    
		  int ID = 0;
		    
		  while(rs.next()) {
			  ID = rs.getInt("ProductID");
		  }
	      
	      modelTable3.addRow(new Object[]{ID, prodName, Description,price,"Delete"});
	}
	
	public static void addItem() throws SQLException, ClassNotFoundException {
		  String orderID = orderIDField.getText();
		  String productID = productIDField.getText();
		  String quantity = quantityField.getText();
		  String sql = "insert into orderitems values('"+orderID+"','"+productID+"','"+quantity+"')";
	      
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/productorders2","root","christian123");
	      conn.createStatement().executeUpdate(sql);
	      
	      modelTable4.addRow(new Object[]{orderID,productID,quantity,"Delete"});
	}

	public static void main(String [] args) throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.jdbc.Driver");
		 		 
		 orders gui = new orders();
	}
	 
	public class okClick implements ActionListener {
		 public void actionPerformed(ActionEvent o) {	 
		    try {
		    	if(addUser!=null) {
		    		if(addUser.isVisible()) {
		    			addUser(); //addUser function
		    			addUser.setVisible(false); //Removes addUser frame
		    			addUser.dispose();
		    		}
		    	}
		    	if(addOrder!= null) {
		    		if(addOrder.isVisible()) {
		    			addOrder();
		    			addOrder.setVisible(false);
		    			addOrder.dispose();
		    		}
		    	}
		    	if(addProduct!= null) {
		    		if(addProduct.isVisible()) {
		    			addProduct();
		    			addProduct.setVisible(false);
		    			addProduct.dispose();
		    		}
		    	}
		    	if(addItem!= null) {
		    		if(addItem.isVisible()) {
			    		addItem();
			    		addItem.setVisible(false);
						addItem.dispose();
		    		}
		    	}
			} 
		    catch (SQLException e) {
				e.printStackTrace();
			} 
		    catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 	 
		 }

	 }
	 
	public class cancelClick implements ActionListener
	 {
		 public void actionPerformed(ActionEvent c)
		 {
			 if(addUser!=null &&  addUser.isVisible())
			 {
			 addUser.setVisible(false);
			 addUser.dispose();
			 }
			 if(addOrder!=null && addOrder.isVisible())
			 {
			 addOrder.setVisible(false);
			 addOrder.dispose();
			 }
			 if(addProduct!=null && addProduct.isVisible())
			 {
			 addProduct.setVisible(false);
			 addProduct.dispose();
			 }
			 if(addItem!=null && addItem.isVisible())
			 {
			 addItem.setVisible(false);
			 addItem.dispose();
			 }
		 }

	 }
	 
	public class newUserClick implements ActionListener {
		 public void actionPerformed(ActionEvent c) {
			 addUserFrame();
		 }
	 }
	 
	public class newOrderClick implements ActionListener {
		 public void actionPerformed(ActionEvent c) {
			 addOrderFrame();
		 }

	 }
	 
	public class newProductClick implements ActionListener {
		 public void actionPerformed(ActionEvent c) {
			 addProductFrame();
		 }

	 }
	 
	public class newItemAdd implements ActionListener {
		 public void actionPerformed(ActionEvent c) {
			 addItemFrame();
		 }
	 }	 
}

class ButtonRenderer extends JButton implements TableCellRenderer {
	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
}

class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;

	  private String label;

	  private boolean isPushed;

	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	      }
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
	    if (isPushed) {
	      
	      
	      JOptionPane.showMessageDialog(button, label + ": Ouch!");
	      
	    }
	    isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
}