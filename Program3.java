//Name: Jason Hahn
//EID: jh73942


public class Program3 {

    // DO NOT REMOVE OR MODIFY THESE VARIABLES (calculator and treatment_plan)
    ImpactCalculator calculator;    // the impact calculator
    int[] treatment_plan;           // array to store the treatment plan

    public Program3() {
        this.calculator = null;
    }

    /*
     * This method is used in lieu of a required constructor signature to initialize
     * your Program3. After calling a default (no-parameter) constructor, we
     * will use this method to initialize your Program3.
     * 
     *  DO NOT MODIFY THIS METHOD
     * 
     */
    public void initialize(ImpactCalculator ic) {
        this.calculator = ic;
        this.treatment_plan = new int[ic.getNumMedicines()];
    }


    /*
     * This method computes and returns the total impact of the treatment plan. It should
     * also fill in the treatment_plan array with the correct values.
     * 
     * Each element of the treatment_plan array should contain the number of hours
     * that medicine i should be administered for. For example, if treatment_plan[2] = 5,
     * then medicine 2 should be administered for 5 hours.
     * 
     */

     public int computeImpact() {
        int n = calculator.getNumMedicines();
        int H = calculator.getTotalTime();
        int[][] dp = new int[n+1][H+1];
        int[][][] bt = new int[n+1][H+1][2];
    
        for (int med = 1; med <= n; med++) {
            for (int hour = 1; hour <= H; hour++) {
                for (int newmedh = 0; newmedh <= hour; newmedh++) {
                    if(dp[med][hour] < dp[med-1][hour-newmedh] + calculator.calculateImpact(med - 1, newmedh)){
                        dp[med][hour] = dp[med-1][hour-newmedh] + calculator.calculateImpact(med - 1, newmedh);
                        bt[med][hour][0] = newmedh;
                        bt[med][hour][1] = hour - newmedh;
                    }
                }
            }
        }
        
        int timeRemaining = H;
        for(int i = n; i > 0; i--){
            treatment_plan[i-1] = bt[i][timeRemaining][0];
            timeRemaining = bt[i][timeRemaining][1];
        }

        return dp[n][H];
    }


    /*
     * This method prints the treatment plan.
     */
    public void printTreatmentPlan() {
        System.out.println("Please administer medicines 1 through n for the following amounts of time:\n");
        int hoursForI = 0;
        int n = calculator.getNumMedicines();
        for(int i = 0; i < n; i++){
            // retrieve the amount of hours for medicine i
            hoursForI = treatment_plan[i]; // ... fill in here ...
            System.out.println("Medicine " + i + ": " + hoursForI); 
        }
    }
}


