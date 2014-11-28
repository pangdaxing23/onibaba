package databaseObjects.beans.Person;
import BaseMVC.BasicModel;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class PersonModel extends BasicModel {
	
	private String ssn = null;
	private String first_name = null;
	private String last_name = null;
	private Date birthday = null;
	private Integer phone = 0;
	private String email = null;
	private String title = null;
	private Integer person_id = -1;

	// required properties 
	private static String[] requiredOnInsert = {"ssn", "first_name", "last_name", "email", "title", "phone", "birthday" };
	protected static String[][] modelRules = { 
		{"ssn", "string"},
		{"first_name", "string"},
		{"first_name", "uppercase"},
		{"last_name", "string"},
		{"last_name", "uppercase"},
		{"email", "string"},
		{"email", "string"},
		{"email", "email"},
		{"title", "string"},
		{"phone", "integer"},
		{"phone", "positive" },
		{"birthday", "date"},
		{"birthday", "past" },
		{"id", "integer"},
		{"id", "positive" },
		{"nurse_id", "integer"},
		{"nurse_id", "positive" },
		{"doctor_id", "integer"},
		{"doctor_id", "positive" },
		{"education", "string"},
		{"experience", "string"}
	};
	private static final String TABLENAME = "persons";
	// fillable from the front end properties
	private static String[] fillables = {"ssn", "first_name", "last_name", "email", "title", "phone", "birthday", "id"};
	private static String[] hasMany = {"appointment","comment"};
	
	public PersonModel(int person_id) throws SQLException
	{
		super("person", person_id);
		ResultSet attributes = queryRunner.runQuery("SELECT * FROM persons WHERE `id`=\""+person_id+"\";", false);
		hasManyInstances = hasMany;
		person_id = attributes.getInt("id");
		ssn = attributes.getString("ssn");
		first_name = attributes.getString("first_name");
		last_name = attributes.getString("last_name");
		birthday = attributes.getDate("birthday");
		phone = attributes.getInt("phone");
		email = attributes.getString("email");
		title = attributes.getString("title");
		

	}
	


	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}



	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}



	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}



	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}



	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}



	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	/**
	 * @return the phone
	 */
	public Integer getPhone() {
		return phone;
	}



	/**
	 * @param phone the phone to set
	 */
	public void setPhone(Integer phone) {
		this.phone = phone;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the person_id
	 */
	public Integer getPerson_id() {
		return person_id;
	}



	public ResultSet appointments() throws SQLException
	{
		return hasMany("appointment");
	}
	public ResultSet comments() throws SQLException
	{
		ConcurrentHashMap<String, String> temp = new ConcurrentHashMap<String, String>();
		temp.put("person_id", ""+person_id);
		return specials(temp, "comments");
	}
	public ResultSet reminders() throws SQLException
	{
		ConcurrentHashMap<String, String> temp = new ConcurrentHashMap<String, String>();
		temp.put("person_id", ""+person_id);
		temp.put("object_model", "person");
		temp.put("object_id", ""+person_id);
		return specials(temp, "comments");
	}
	
	// model queries.
	public static String getInsertStatement(ConcurrentHashMap<String,String> attributes)
	{
		attributes = validateData(attributes, "insert");
		if ( attributes == null) {
			System.out.println("Your input are missing some of the required fields for inserts.");
			return null;
		}
		return getInsertStatement(attributes, TABLENAME);
	}

	public static String getDeleteStatement(ConcurrentHashMap<String,String> finders)
	{
		finders = validateData(finders, null);
		if (finders == null) {
			System.out.println("None of the inputed data were valid. Please try again.");
			return null;
		}
		return getDeleteStatement(finders, TABLENAME);
	}

	public static String getFindStatement(ConcurrentHashMap<String,String> finders)
	{
		finders = validateData(finders, null);
		if (finders == null) {
			System.out.println("None of the inputed data were valid. Please try again.");
			return null;
		}
		return getFindStatement(finders, TABLENAME);
	}

	public static String getUpdateStatement(ConcurrentHashMap<String, String> finders, ConcurrentHashMap<String, String> attributes)
	{
		attributes = validateData(attributes, null);
		if (attributes == null) {
			System.out.println("None of the inputed data for your attributes were valid. Please try again.");
			return null;
		}
		finders = validateData(finders, null);
		if (finders == null) {
			System.out.println("None of the inputed data for your finders were valid. Please try again.");
			return null;
		}
		return getUpdateStatement(finders, attributes, TABLENAME);
	}
	
	
	// Validating the data.
	// removes unfillable fields and watches out for sql data.
	protected static ConcurrentHashMap<String, String> validateData(ConcurrentHashMap<String, String> attributes, String methodName)
	{
		// attributes.keySet().toArray();
		String[] keys = attributes.keySet().toArray(new String[attributes.size()]);
		ConcurrentHashMap<String, ArrayList<String>> fieldRules = new ConcurrentHashMap<String, ArrayList<String>>(); 
		
		// then we it could be anything except an insert statement. In which case all we have to do is make sure the attributes are fillable
		if (methodName == null) {

			for( int i = 0; i< keys.length; i++)
			{		
				// if it contains and the value isn't null we keep it
				if (Arrays.asList(fillables).contains(keys[i]))  {

					if (fieldRules.get(keys[i]) == null) {
						fieldRules.put(keys[i], new ArrayList<String>());
					}

					// if it is part of the fillable arrays then it has a set of rules that apply to it.
					for( int k = 0; k < modelRules.length; k++) {

						if (modelRules[k][0].equals(keys[i])) {
							fieldRules.get(keys[i]).add( modelRules[k][1]);
						}
					}
					
					String value = evaluateFieldRule(attributes.get(keys[i]), fieldRules.get(keys[i]).toArray(new String[fieldRules.get(keys[i]).size()]));
					
					if ( value == null) {
						System.out.println("The field "+keys[i]+" has a null value. Therefore it is being discarder.");
						attributes.remove(keys[i]);
						// If we are inserting and the field is required, we cancel and tell them to retry.
						if (methodName == "insert" && Arrays.asList(requiredOnInsert).contains(keys[i])) {
							System.out.println("However the field "+keys[i]+ " is required. Please try again.");
							return null;
						}

					} else {
						attributes.replace(keys[i], value);
					}
					continue;
				}
				// else we have an ambiguous field name, and we must remove them.
				attributes.remove(keys[i]);
			}
		}
		return attributes;
	}

	// Tester
	public static void main(String[] args)
	{
		// First let's test that 
		String findStatement;
		ConcurrentHashMap<String, String> attributes = new ConcurrentHashMap<String, String>();
		attributes.put("description", "All for one and one for all.");
		attributes.put("id", "1");
		findStatement = PersonModel.getDeleteStatement(attributes);
		System.out.println(findStatement);
		PersonModel.closeDbConnection();
	}
}

