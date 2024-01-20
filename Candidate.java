/**
* Class storing the data for a democratic presidential candidate.
* @author Iris
*/
public class Candidate implements Comparable<Candidate> {
    private String lastName;
    private String fullName;
    private float percentage;
    private int mostRecentDate;

    /** 
    * Constructor for Candidates.
    * @param lastName - candidate's last name
    * @param fullName - candidate's full name
    * @param pct - the percentage of votes they received
    * @param date - the date of the most recent poll
    * in the format YYYYMMDD_#
    */
    public Candidate(String lastName, String fullName, float pct, int date) {
        this.lastName = lastName;
        this.fullName = fullName;
        this.percentage = pct;
        this.mostRecentDate = date;
    }

    /** 
    * Constructs a Candidate with only a name.
    * @param lastName - candidate's last name
    */
    public Candidate(String lastName) {
        this.lastName = lastName;
        this.fullName = lastName;
        this.percentage = 0;
        this.mostRecentDate = 0;
    }

    /** 
    * Compares the candidates alphabetically by last name.
    * @param other - the candidate to compare to
    * @return zero if the candidates have the same name,
    * a negative number if the first candidate comes before the second,
    * a positive number otherwise
    */
    public int compareTo(Candidate other) {
        return lastName.compareTo(other.lastName);
    }

    /** 
    * Creates a string representation of the object.
    * @return the string representation
    */
    public String toString() {
        return fullName + ":" + percentage;
    }
}