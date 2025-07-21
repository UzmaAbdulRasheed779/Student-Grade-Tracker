package CodeAlpha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import CodeAlpha.Student;

public class StudentGradeTracker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Student> students= new ArrayList<>();
	
		/////create a frame 
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(500,500);
		
		jf.setTitle("📘 Student Grade Tracker");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/////label for name 
		JLabel name = new JLabel("Enter Student Name");
		name.setBounds(20,20,150,30);
		jf.add(name);
		
		///////for textfield
		JTextField tf = new JTextField();
		tf.setBounds(180, 20, 200, 30);
		jf.add(tf);
		
		/////label for grade 
		JLabel grade = new JLabel("Enter Student Grade");
		grade.setBounds(20, 60, 150, 30);
		jf.add(grade);
		
		///////for textfield
		JTextField tg = new JTextField();
		tg.setBounds(180, 60, 200, 30);
		jf.add(tg);
		
		
		//////////to make a button 
		JButton button = new JButton("Add Student");
		button.setBounds(180, 110, 200, 30);
		jf.add(button);
		
		///reportbutton
		JButton report = new JButton("Show Report");
		report.setBounds(180, 160, 200, 30);
		jf.add(report);
		
		//////////text area
		
		JTextArea ta = new JTextArea();
		ta.setBounds(20, 210, 440, 220);
		ta.setEditable(false);
		jf.add(ta);
		

		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String sname = tf.getText();
		        String gradeText = tg.getText();

		        if (sname.isEmpty() || gradeText.isEmpty()) {
		            JOptionPane.showMessageDialog(jf, "Please enter both name and grade.");
		            return;
		        }

		        try {
		            int igrade = Integer.parseInt(gradeText);
		            students.add(new Student(sname, igrade));

		       /////// Update text area with the new entry
		            ta.append(sname + " - " + igrade + "\n");

		            ///////// Clear the input fields
		            tf.setText("");
		            tg.setText("");

		            JOptionPane.showMessageDialog(jf, "Student Added");
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(jf, "Please enter a valid number for grade.");
		        }
		    }
		});

		/////show report
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(students.isEmpty()) {
					ta.setText("No Student added");
				return;
				}
				StringBuilder sb = new StringBuilder();
				int total = 0;
				int highest = Integer.MIN_VALUE; 
				int lowest = Integer.MAX_VALUE; 

				for(Student s  : students) {
					sb.append(s.name).append(" -").append(s.grade).append("\n");
					total += s.grade;
					if (s.grade>highest) highest = s.grade;
					if(s.grade<lowest) lowest = s.grade;
				}
				
				 double average = (double) total / students.size();
	                sb.append("\nAverage: ").append(String.format("%.2f", average));
	                sb.append("\nHighest: ").append(highest);
	                sb.append("\nLowest: ").append(lowest);

	                ta.setText(sb.toString());
			}
		});

		
		jf.setVisible(true);
			
		}
	}