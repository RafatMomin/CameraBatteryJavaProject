package hw1;

public class CameraBattery {
	public static final int NUM_CHARGER_SETTINGS  = 4;
	public static final double CHARGE_RATE = 2.0;
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;



    private int ZERO = 0;
    //public static final double BATTERY_CAPACITY = ;

    //private double batteryStartingCharge;

    private double batteryCapacity;

    private double batteryCharge;

    private double totalDrain;

    private double wallWartBatteryCapacity;

    private double wallWartBatterycharge;

    private double cameraCapacity;

    private int chargerSettingNumber;

    private double powerConsumption;

    

    private double cameraCharge;

    public CameraBattery(double batteryStartingCharge,double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        batteryCharge = Math.min(batteryStartingCharge, batteryCapacity);
        cameraCharge = ZERO;
        totalDrain = ZERO;
        wallWartBatteryCapacity = ZERO;
        cameraCapacity = ZERO;
        cameraCapacity = ZERO;
        wallWartBatterycharge = ZERO;
        powerConsumption = DEFAULT_CAMERA_POWER_CONSUMPTION;
    }
    /*Indicates the user has pressed the setting button one time on the external charger. The charge
    setting increments by one or if already at the maximum setting wraps around to setting 0.
    	 * takes no param
    	 * returns nothing
    	 */
    public void buttonPress() {
        chargerSettingNumber = (chargerSettingNumber+1) % NUM_CHARGER_SETTINGS ;
    }
    /*Charges the battery connected to the camera (assuming it is connected) for a given number of
    minutes. The amount of charging in milliamp-hours (mAh) is the number minutes times the
    CHARGE_RATE constant. However, charging cannot exceed the capacity of the battery
    connected to the camera, or no charging if the battery is not connected. The method returns the
    actual amount the battery has been charged.
    	 * @param minutes in double
    	 * @return charging amount in double
    	 * 
    	 */
    public double cameraCharge(double minutes) {
        double previousCameraCharge = cameraCharge;
        double chargeAmount;
        cameraCharge = Math.min(cameraCharge + minutes * CHARGE_RATE, cameraCapacity);
        chargeAmount = cameraCharge - previousCameraCharge;
        batteryCharge = batteryCharge + chargeAmount;
        return chargeAmount;
    }
    /*Drains the battery connected to the camera (assuming it is connected) for a given number of
    minutes. The amount of drain in milliamp-hours (mAh) is the number of minutes times the
    cameras power consumption. However, the amount cannot exceed the amount of charge
    contained in the battery assuming it is connected to the camera, or no amount drain if the battery
    is not connected. The method returns the actual amount drain from the battery.
     * 
     * 
     * @param minutes in double
     * @return batteryDrain in double
     */
    public double drain(double minutes) {
        double previousCameraCharge = cameraCharge;
        double drainAmount;
        cameraCharge = Math.max(cameraCharge - minutes * getCameraPowerConsumption(), ZERO);
        drainAmount = previousCameraCharge - cameraCharge;
        batteryCharge = batteryCharge - drainAmount;
        totalDrain = totalDrain + drainAmount;
        return drainAmount;
    }
    /*Charges the battery connected to the external charger (assuming it is connected) for a given
    number of minutes. The amount of charging in milliamp-hours (mAh) is the number minutes
    times the charger setting (a number between 0 inclusive and NUM_CHARGER_SETTINGS
    exclusive) the CHARGE_RATE constant. However, charging cannot exceed the capacity of the
    battery connected to the camera, or no charging if the battery is not connected. The method
    returns the actual amount the battery has been charged. 
    	 * 
    	 * 
    	 * @param minutes in double
    	 * @return wallWartBatterycharge in double
    	 */
    	
    public double externalCharge(double minutes) {
        double previousCharge = wallWartBatterycharge;
        double chargeAmount;
        wallWartBatterycharge = Math.min(wallWartBatterycharge + minutes * chargerSettingNumber * CHARGE_RATE, wallWartBatteryCapacity) ;
        chargeAmount = wallWartBatterycharge - previousCharge;
        batteryCharge = batteryCharge + chargeAmount;
        return chargeAmount;
    }
    /*Reset the battery monitoring system by setting the total battery drain count back to 0
	 * takes no param
	 * mutator function
	 */
    public void resetBatteryMonitor() {
        totalDrain = ZERO;
    }
    /*
	 * Get the battery's capacity
	 * takes no param
	 * @return batteryCapacity in double
	 */
    public double getBatteryCapacity() {
        return batteryCapacity;
    }
    /*Get the battery's capacity
     * takes no param
     * @return batteryCharge in double
     */
    public double getBatteryCharge() {
        return batteryCharge;
    }
	/*Get the battery's current charge.
	 * takes no param
	 * @return cameraCharge in double
	 */
    public double getCameraCharge() {
        return cameraCharge;
    }
    /*Get the power consumption of the camera.
	 * takes no param
	 * @return powerConsumption in double
	 */
    public double getCameraPowerConsumption() {
        return powerConsumption;
    }
    /*Get the external charger setting
	 * takes no param
	 * @return chargerSettingNumber in double
	 */
    public int getChargerSetting() {
        return chargerSettingNumber;
    }
    /*Get the total amount of power drained from the battery since the last time the battery monitor
    was started or reset
    	 * takes no param
    	 * @return totalDrain in double
    	 */
    public double getTotalDrain() {
        return totalDrain;
    }
    /*Move the battery to the external charger. Updates any variables as needed to represent the move.
     * takes no param
     * void returns nothing
     */
    public void moveBatteryExternal() {
        cameraCharge = ZERO;
        cameraCapacity = ZERO;
        wallWartBatterycharge = batteryCharge;
        wallWartBatteryCapacity = batteryCapacity;
    }
    /*Move the battery to the camera. Updates any variables as needed to represent the move
     * takes no param
     * void returns nothing
     */
    public void moveBatteryCamera() {
        cameraCharge = batteryCharge;
        cameraCapacity = batteryCapacity;
        wallWartBatterycharge = ZERO;
        wallWartBatteryCapacity = ZERO;
    }
    /*Remove the battery from either the camera or external charger. Updates any variables as needed
    to represent the removal.
    	 * @takes no param
    	 * returns nothing
    	 */
    public void removeBattery() {
        cameraCharge = ZERO;
        cameraCapacity = ZERO;
        wallWartBatterycharge = ZERO;
        wallWartBatteryCapacity = ZERO;

    }
    /*Set the power consumption of the camera.
	 * @param cameraPowerConsumption as double
	 * returns nothing
	 */
    public void setCameraPowerConsumption(double cameraPowerConsumption) {
        powerConsumption = cameraPowerConsumption;
    }
    public static void main(String args[]) {
        CameraBattery cb = new CameraBattery(1000, 2000);
        System.out.println("Test 1:");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 1000.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 0.0");

        // battery is connected to the camera for next tests
        cb.moveBatteryCamera();
        System.out.println();
        System.out.println("Test 2:");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 1000.0");
        System.out.println("Camera charge is " + cb.getCameraCharge() + " expected 1000.0");

        // before calling drain
        System.out.println("Total drain is " + cb.getTotalDrain()
                + " expected 0.0");
        System.out.println("Camera power consumption is "
                + cb.getCameraPowerConsumption() + " expected " + 1.0);
        double drained = cb.drain(100.0);
        System.out.println();
        System.out.println("Test 4:");
        System.out.println("Battery drained by " + drained + " expected 100.0");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 900.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 900.0");
        System.out.println("Total drain is " + cb.getTotalDrain()
                + " expected 100.0");
        drained = cb.drain(1000.0);
        System.out.println();
        System.out.println("Test 5:");
        System.out.println("Battery drained by " + drained + " expected 900.0");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 0.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 0.0");
        System.out.println("Total drain is " + cb.getTotalDrain()
                + " expected 1000.0");

        double charged = cb.cameraCharge(50.0);
        System.out.println();
        System.out.println("Test 6:");
        System.out.println("Battery charged by " + charged + " expected 100.0");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 100.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 100.0");

        // battery is removed for the next couple tests
        cb.removeBattery();
        drained = cb.drain(50.0);
        System.out.println();
        System.out.println("Test 7:");
        System.out.println("Battery drained by " + drained + " expected 0.0");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 100.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 0.0");
        System.out.println("Total drain is " + cb.getTotalDrain()
                + " expected 1000.0");
        cb.cameraCharge(50.0);
        System.out.println();
        System.out.println("Test 8:");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 100.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 0.0");
        System.out.println("Total drain is " + cb.getTotalDrain()
                + " expected 1000.0");
        // battery is moved to the external charger for the next few tests
        cb.moveBatteryExternal();
        cb.externalCharge(50.0);
        System.out.println();
        System.out.println("Test 9:");
        System.out.println("External charger setting is " + cb.getChargerSetting()
                + " exptected 0");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 100.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 0.0");
        System.out.println("Total drain is " + cb.getTotalDrain()
                + " expected 1000.0");
        cb.buttonPress();
        cb.buttonPress();
        cb.externalCharge(50.0);
        System.out.println();
        System.out.println("Test 10:");
        System.out.println("External charger setting is " + cb.getChargerSetting()
                + " exptected 2");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 300.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 0.0");
        System.out.println("Total drain is " + cb.getTotalDrain()
                + " expected 1000.0");
        cb.buttonPress();
        cb.buttonPress();
        cb.externalCharge(50.0);
        System.out.println();
        System.out.println("Test 11:");
        System.out.println("External charger setting is "
                + cb.getChargerSetting() + " exptected 0");
        System.out.println("Battery charge is " + cb.getBatteryCharge()
                + " expected 300.0");
        System.out.println("Camera charge is " + cb.getCameraCharge()
                + " expected 0.0");
        System.out.println("Total drain is " + cb.getTotalDrain()
                + " expected 1300.0");


    }



}