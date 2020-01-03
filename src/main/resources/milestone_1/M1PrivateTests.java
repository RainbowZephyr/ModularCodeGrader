import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class M1PrivateTests {
    String simulatorPath = "simulation.Simulator";
    String addressPath = "simulation.Address";
    String rescuablePath = "simulation.Rescuable";
    String worldEntityPath = "simulation.WorldEntity";
    String residentialBuildingPath = "model.infrastructure.ResidentialBuilding";
    String citizenStatePath = "model.people.CitizenState";
    String unitStatePath = "model.units.UnitState";
    String citizenPath = "model.people.Citizen";
    String unitPath = "model.units.Unit";
    String policeUnitPath = "model.units.PoliceUnit";
    String fireUnitPath = "model.units.FireUnit";
    String medicalUnitPath = "model.units.MedicalUnit";
    String evacuatorPath = "model.units.Evacuator";
    String fireTruckPath = "model.units.FireTruck";
    String gasControlUnitPath = "model.units.GasControlUnit";
    String ambulancePath = "model.units.Ambulance";
    String diseaseControlUnitPath = "model.units.DiseaseControlUnit";
    String disasterPath = "model.disasters.Disaster";
    String collapsePath = "model.disasters.Collapse";
    String firePath = "model.disasters.Fire";
    String gasLeakPath = "model.disasters.GasLeak";
    String infectionPath = "model.disasters.Infection";
    String injuryPath = "model.disasters.Injury";
    String commandCenterPath = "controller.CommandCenter";

    @Test
    public void testClassIsAbstractUnit() throws Exception {
        testClassIsAbstract(Class.forName(unitPath));
    }

    @Test
    public void testClassIsAbstractMedicalUnit() throws Exception {
        testClassIsAbstract(Class.forName(medicalUnitPath));
    }

    @Test
    public void testClassIsEnumCitizenState() throws Exception {
        testIsEnum(Class.forName(citizenStatePath));
    }

    @Test
    public void testClassIsSubclassPoliceUnit() throws Exception {
        testClassIsSubclass(Class.forName(policeUnitPath), Class.forName(unitPath));
    }

    @Test
    public void testClassIsSubclassEvacuator() throws Exception {
        testClassIsSubclass(Class.forName(evacuatorPath), Class.forName(policeUnitPath));
    }

    @Test
    public void testClassIsSubclassAmbulance() throws Exception {
        testClassIsSubclass(Class.forName(ambulancePath), Class.forName(medicalUnitPath));
    }

    @Test
    public void testClassIsSubclassFire() throws Exception {
        testClassIsSubclass(Class.forName(firePath), Class.forName(disasterPath));
    }

    @Test
    public void testClassIsSubclassInjury() throws Exception {
        testClassIsSubclass(Class.forName(injuryPath), Class.forName(disasterPath));
    }

    @Test
    public void testConstructorSimulator0() throws Exception {
        Class[] inputs = {};
        testConstructorExists(Class.forName(simulatorPath), inputs);
    }

    @Test
    public void testConstructorCitizen0() throws Exception {
        Class[] inputs = {Class.forName(addressPath), String.class, String.class, int.class};
        testConstructorExists(Class.forName(citizenPath), inputs);
    }

    @Test
    public void testConstructorFireUnit0() throws Exception {
        Class[] inputs = {String.class, Class.forName(addressPath), int.class};
        testConstructorExists(Class.forName(fireUnitPath), inputs);
    }

    @Test
    public void testConstructorFireTruck0() throws Exception {
        Class[] inputs = {String.class, Class.forName(addressPath), int.class};
        testConstructorExists(Class.forName(fireTruckPath), inputs);
    }

    @Test
    public void testConstructorDiseaseControlUnit0() throws Exception {
        Class[] inputs = {String.class, Class.forName(addressPath), int.class};
        testConstructorExists(Class.forName(diseaseControlUnitPath), inputs);
    }

    @Test
    public void testConstructorFire0() throws Exception {
        Class[] inputs = {int.class, Class.forName(residentialBuildingPath)};
        testConstructorExists(Class.forName(firePath), inputs);
    }

    @Test
    public void testConstructorInjury0() throws Exception {
        Class[] inputs = {int.class, Class.forName(citizenPath)};
        testConstructorExists(Class.forName(injuryPath), inputs);
    }

    @Test
    public void testEnumValuesCitizenState() throws Exception {
        String[] inputs = {"DECEASED", "RESCUED", "IN_TROUBLE", "SAFE"};
        testEnumValues(Class.forName(citizenStatePath), inputs);
    }

    @Test
    public void testClassIsInterfaceRescuable() throws Exception {
        testIsInterface(Class.forName(rescuablePath));
    }

    @Test
    public void testInstanceVariableSimulatorCurrentCycle() throws Exception {
        testInstanceVariableIsPresent(Class.forName(simulatorPath), "currentCycle", true);
        testInstanceVariableIsPrivate(Class.forName(simulatorPath), "currentCycle");
    }

    @Test
    public void testInstanceVariableSimulatorEmergencyUnits() throws Exception {
        testInstanceVariableIsPresent(Class.forName(simulatorPath), "emergencyUnits", true);
        testInstanceVariableIsPrivate(Class.forName(simulatorPath), "emergencyUnits");
    }

    @Test
    public void testInstanceVariableSimulatorExecutedDisasters() throws Exception {
        testInstanceVariableIsPresent(Class.forName(simulatorPath), "executedDisasters", true);
        testInstanceVariableIsPrivate(Class.forName(simulatorPath), "executedDisasters");
    }

    @Test
    public void testInstanceVariableResidentialBuildingLocation() throws Exception {
        testInstanceVariableIsPresent(Class.forName(residentialBuildingPath), "location", true);
        testInstanceVariableIsPrivate(Class.forName(residentialBuildingPath), "location");
    }

    @Test
    public void testInstanceVariableResidentialBuildingGasLevel() throws Exception {
        testInstanceVariableIsPresent(Class.forName(residentialBuildingPath), "gasLevel", true);
        testInstanceVariableIsPrivate(Class.forName(residentialBuildingPath), "gasLevel");
    }

    @Test
    public void testInstanceVariableResidentialBuildingDisaster() throws Exception {
        testInstanceVariableIsPresent(Class.forName(residentialBuildingPath), "disaster", true);
        testInstanceVariableIsPrivate(Class.forName(residentialBuildingPath), "disaster");
    }

    @Test
    public void testInstanceVariableCitizenName() throws Exception {
        testInstanceVariableIsPresent(Class.forName(citizenPath), "name", true);
        testInstanceVariableIsPrivate(Class.forName(citizenPath), "name");
    }

    @Test
    public void testInstanceVariableCitizenHp() throws Exception {
        testInstanceVariableIsPresent(Class.forName(citizenPath), "hp", true);
        testInstanceVariableIsPrivate(Class.forName(citizenPath), "hp");
    }

    @Test
    public void testInstanceVariableCitizenLocation() throws Exception {
        testInstanceVariableIsPresent(Class.forName(citizenPath), "location", true);
        testInstanceVariableIsPrivate(Class.forName(citizenPath), "location");
    }

    @Test
    public void testInstanceVariableUnitTarget() throws Exception {
        testInstanceVariableIsPresent(Class.forName(unitPath), "target", true);
        testInstanceVariableIsPrivate(Class.forName(unitPath), "target");
    }

    @Test
    public void testInstanceVariablePoliceUnitPassengers() throws Exception {
        testInstanceVariableIsPresent(Class.forName(policeUnitPath), "passengers", true);
        testInstanceVariableIsPrivate(Class.forName(policeUnitPath), "passengers");
    }

    @Test
    public void testInstanceVariableMedicalUnitHealingAmount() throws Exception {
        testInstanceVariableIsPresent(Class.forName(medicalUnitPath), "healingAmount", true);
        testInstanceVariableIsPrivate(Class.forName(medicalUnitPath), "healingAmount");
    }

    @Test
    public void testInstanceVariableDisasterTarget() throws Exception {
        testInstanceVariableIsPresent(Class.forName(disasterPath), "target", true);
        testInstanceVariableIsPrivate(Class.forName(disasterPath), "target");
    }

    @Test
    public void testInstanceVariableCommandCenterVisibleBuildings() throws Exception {
        testInstanceVariableIsPresent(Class.forName(commandCenterPath), "visibleBuildings", true);
        testInstanceVariableIsPrivate(Class.forName(commandCenterPath), "visibleBuildings");
    }

    @Test
    public void testUnitUnitIDNotInClasses() throws Exception {
        testInstanceVariableIsPresent(Class.forName(gasControlUnitPath), "Unit.unitID", false);
        testInstanceVariableIsPresent(Class.forName(policeUnitPath), "Unit.unitID", false);
        testInstanceVariableIsPresent(Class.forName(evacuatorPath), "Unit.unitID", false);
        testInstanceVariableIsPresent(Class.forName(fireUnitPath), "Unit.unitID", false);
        testInstanceVariableIsPresent(Class.forName(fireTruckPath), "Unit.unitID", false);
        testInstanceVariableIsPresent(Class.forName(medicalUnitPath), "Unit.unitID", false);
        testInstanceVariableIsPresent(Class.forName(ambulancePath), "Unit.unitID", false);
        testInstanceVariableIsPresent(Class.forName(diseaseControlUnitPath), "Unit.unitID", false);
    }

    @Test
    public void testUnitTargetNotInClasses() throws Exception {
        testInstanceVariableIsPresent(Class.forName(gasControlUnitPath), "Unit.target", false);
        testInstanceVariableIsPresent(Class.forName(policeUnitPath), "Unit.target", false);
        testInstanceVariableIsPresent(Class.forName(evacuatorPath), "Unit.target", false);
        testInstanceVariableIsPresent(Class.forName(fireUnitPath), "Unit.target", false);
        testInstanceVariableIsPresent(Class.forName(fireTruckPath), "Unit.target", false);
        testInstanceVariableIsPresent(Class.forName(medicalUnitPath), "Unit.target", false);
        testInstanceVariableIsPresent(Class.forName(ambulancePath), "Unit.target", false);
        testInstanceVariableIsPresent(Class.forName(diseaseControlUnitPath), "Unit.target", false);
    }

    @Test
    public void testDisasterStartCycleNotInClasses() throws Exception {
        testInstanceVariableIsPresent(Class.forName(injuryPath), "Disaster.startCycle", false);
        testInstanceVariableIsPresent(Class.forName(firePath), "Disaster.startCycle", false);
        testInstanceVariableIsPresent(Class.forName(collapsePath), "Disaster.startCycle", false);
        testInstanceVariableIsPresent(Class.forName(infectionPath), "Disaster.startCycle", false);
        testInstanceVariableIsPresent(Class.forName(gasLeakPath), "Disaster.startCycle", false);
    }

    @Test
    public void testResidentialBuildingImplementsRescuable() throws Exception {
        testClassImplementsInterface(Class.forName(residentialBuildingPath), Class.forName(rescuablePath));
    }

    @Test
    public void testCitizenImplementsWorldEntity() throws Exception {
        testClassImplementsInterface(Class.forName(citizenPath), Class.forName(worldEntityPath));
    }

    @Test
    public void testInstanceVariableSimulatorCurrentCycleGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(simulatorPath), "getCurrentCycle", int.class, true);
        testSetterMethodExistsInClass(Class.forName(simulatorPath), "setCurrentCycle", int.class, false);
    }

    @Test
    public void testInstanceVariableSimulatorEmergencyUnitsGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(simulatorPath), "getEmergencyUnits", ArrayList.class, false);
        testSetterMethodExistsInClass(Class.forName(simulatorPath), "setEmergencyUnits", ArrayList.class, false);
    }

    @Test
    public void testInstanceVariableSimulatorExecutedDisastersGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(simulatorPath), "getExecutedDisasters", ArrayList.class, false);
        testSetterMethodExistsInClass(Class.forName(simulatorPath), "setExecutedDisasters", ArrayList.class, false);
    }

    @Test
    public void testInstanceVariableResidentialBuildingLocationGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(residentialBuildingPath), "getLocation", Class.forName(addressPath), true);
        testSetterMethodExistsInClass(Class.forName(residentialBuildingPath), "setLocation", Class.forName(addressPath), false);
    }

    @Test
    public void testInstanceVariableResidentialBuildingGasLevelGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(residentialBuildingPath), "getGasLevel", int.class, true);
        testSetterMethodExistsInClass(Class.forName(residentialBuildingPath), "setGasLevel", int.class, true);
    }

    @Test
    public void testInstanceVariableResidentialBuildingDisasterGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(residentialBuildingPath), "getDisaster", Class.forName(disasterPath), true);
        testSetterMethodExistsInClass(Class.forName(residentialBuildingPath), "setDisaster", Class.forName(disasterPath), true);
    }

    @Test
    public void testInstanceVariableCitizenNameGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(citizenPath), "getName", String.class, true);
        testSetterMethodExistsInClass(Class.forName(citizenPath), "setName", String.class, true);
    }

    @Test
    public void testInstanceVariableCitizenHpGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(citizenPath), "getHp", int.class, true);
        testSetterMethodExistsInClass(Class.forName(citizenPath), "setHp", int.class, true);
    }

    @Test
    public void testInstanceVariableCitizenLocationGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(citizenPath), "getLocation", Class.forName(addressPath), true);
        testSetterMethodExistsInClass(Class.forName(citizenPath), "setLocation", Class.forName(addressPath), false);
    }

    @Test
    public void testInstanceVariableUnitTargetGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(unitPath), "getTarget", Class.forName(rescuablePath), true);
        testSetterMethodExistsInClass(Class.forName(unitPath), "setTarget", Class.forName(rescuablePath), false);
    }

    @Test
    public void testInstanceVariablePoliceUnitPassengersGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(policeUnitPath), "getPassengers", ArrayList.class, false);
        testSetterMethodExistsInClass(Class.forName(policeUnitPath), "setPassengers", ArrayList.class, false);
    }

    @Test
    public void testInstanceVariableMedicalUnitHealingAmountGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(medicalUnitPath), "getHealingAmount", int.class, false);
        testSetterMethodExistsInClass(Class.forName(medicalUnitPath), "setHealingAmount", int.class, false);
    }

    @Test
    public void testInstanceVariableDisasterTargetGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(disasterPath), "getTarget", Class.forName(rescuablePath), true);
        testSetterMethodExistsInClass(Class.forName(disasterPath), "setTarget", Class.forName(rescuablePath), false);
    }

    @Test
    public void testInstanceVariableCommandCenterVisibleBuildingsGetterAndSetter() throws Exception {
        testGetterMethodExistsInClass(Class.forName(commandCenterPath), "getVisibleBuildings", ArrayList.class, false);
        testSetterMethodExistsInClass(Class.forName(commandCenterPath), "setVisibleBuildings", ArrayList.class, false);
    }

    @Test
    public void testInstanceVariableAddressXGetterLogic0() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        testGetterLogic(address0, "x", 6);
    }

    @Test
    public void testInstanceVariableAddressYGetterLogic0() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        testGetterLogic(address0, "y", 6);
    }

    @Test
    public void testInstanceVariableCitizenStateGetterLogic0() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testGetterLogic(citizen0, "state", Enum.valueOf((Class<Enum>) Class.forName(citizenStatePath), "SAFE"));
    }

    @Test
    public void testInstanceVariableCitizenNameGetterLogic0() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testGetterLogic(citizen0, "name", String7);
    }

    @Test
    public void testInstanceVariableCitizenNationalIDGetterLogic0() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testGetterLogic(citizen0, "nationalID", String4);
    }

    @Test
    public void testInstanceVariableCitizenAgeGetterLogic0() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testGetterLogic(citizen0, "age", 0);
    }

    @Test
    public void testInstanceVariableCitizenHpGetterLogic0() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testGetterLogic(citizen0, "hp", 100);
    }

    @Test
    public void testInstanceVariableCitizenBloodLossGetterLogic0() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testGetterLogic(citizen0, "bloodLoss", 0);
    }

    @Test
    public void testInstanceVariableCitizenToxicityGetterLogic0() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testGetterLogic(citizen0, "toxicity", 0);
    }

    @Test
    public void testInstanceVariableCitizenLocationGetterLogic0() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testGetterLogic(citizen0, "location", address2);
    }

    @Test
    public void testInstanceVariableResidentialBuildingLocationGetterLogic0() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testGetterLogic(residentialBuilding0, "location", address0);
    }

    @Test
    public void testInstanceVariableResidentialBuildingStructuralIntegrityGetterLogic0() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testGetterLogic(residentialBuilding0, "structuralIntegrity", 100);
    }

    @Test
    public void testInstanceVariableResidentialBuildingFireDamageGetterLogic0() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testGetterLogic(residentialBuilding0, "fireDamage", 0);
    }

    @Test
    public void testInstanceVariableResidentialBuildingGasLevelGetterLogic0() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testGetterLogic(residentialBuilding0, "gasLevel", 0);
    }

    @Test
    public void testInstanceVariableResidentialBuildingFoundationDamageGetterLogic0() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testGetterLogic(residentialBuilding0, "foundationDamage", 0);
    }

    @Test
    public void testInstanceVariableEvacuatorMaxCapacityGetterLogic0() throws Exception {
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        int int3 = 3;
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        testGetterLogic(evacuator0, "maxCapacity", 3);
    }

    @Test
    public void testInstanceVariableEvacuatorDistToBaseGetterLogic0() throws Exception {
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        int int3 = 3;
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        testGetterLogic(evacuator0, "distToBase", 0);
    }

    @Test
    public void testInstanceVariableEvacuatorUnitIDGetterLogic0() throws Exception {
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        int int3 = 3;
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        testGetterLogic(evacuator0, "unitID", String7);
    }

    @Test
    public void testInstanceVariableEvacuatorLocationGetterLogic0() throws Exception {
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        int int3 = 3;
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        testGetterLogic(evacuator0, "location", address1);
    }

    @Test
    public void testInstanceVariableEvacuatorDistanceToTargetGetterLogic0() throws Exception {
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        int int3 = 3;
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        testGetterLogic(evacuator0, "distanceToTarget", 0);
    }

    @Test
    public void testInstanceVariableEvacuatorStepsPerCycleGetterLogic0() throws Exception {
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        int int3 = 3;
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        testGetterLogic(evacuator0, "stepsPerCycle", 0);
    }

    @Test
    public void testInstanceVariableFireTruckUnitIDGetterLogic0() throws Exception {
        String String8 = "hid";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object fireTruck0 = Class.forName(fireTruckPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String8, address1, int0);
        testGetterLogic(fireTruck0, "unitID", String8);
    }

    @Test
    public void testInstanceVariableFireTruckLocationGetterLogic0() throws Exception {
        String String8 = "hid";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object fireTruck0 = Class.forName(fireTruckPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String8, address1, int0);
        testGetterLogic(fireTruck0, "location", address1);
    }

    @Test
    public void testInstanceVariableFireTruckDistanceToTargetGetterLogic0() throws Exception {
        String String8 = "hid";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object fireTruck0 = Class.forName(fireTruckPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String8, address1, int0);
        testGetterLogic(fireTruck0, "distanceToTarget", 0);
    }

    @Test
    public void testInstanceVariableFireTruckStepsPerCycleGetterLogic0() throws Exception {
        String String8 = "hid";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object fireTruck0 = Class.forName(fireTruckPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String8, address1, int0);
        testGetterLogic(fireTruck0, "stepsPerCycle", 0);
    }

    @Test
    public void testInstanceVariableGasControlUnitUnitIDGetterLogic0() throws Exception {
        String String4 = "kewp";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int7 = 7;
        Object gasControlUnit0 = Class.forName(gasControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String4, address2, int7);
        testGetterLogic(gasControlUnit0, "unitID", String4);
    }

    @Test
    public void testInstanceVariableGasControlUnitLocationGetterLogic0() throws Exception {
        String String4 = "kewp";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int7 = 7;
        Object gasControlUnit0 = Class.forName(gasControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String4, address2, int7);
        testGetterLogic(gasControlUnit0, "location", address2);
    }

    @Test
    public void testInstanceVariableGasControlUnitDistanceToTargetGetterLogic0() throws Exception {
        String String4 = "kewp";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int7 = 7;
        Object gasControlUnit0 = Class.forName(gasControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String4, address2, int7);
        testGetterLogic(gasControlUnit0, "distanceToTarget", 0);
    }

    @Test
    public void testInstanceVariableGasControlUnitStepsPerCycleGetterLogic0() throws Exception {
        String String4 = "kewp";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int7 = 7;
        Object gasControlUnit0 = Class.forName(gasControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String4, address2, int7);
        testGetterLogic(gasControlUnit0, "stepsPerCycle", 7);
    }

    @Test
    public void testInstanceVariableAmbulanceUnitIDGetterLogic0() throws Exception {
        String String9 = "fzgmb";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int9 = 9;
        Object ambulance0 = Class.forName(ambulancePath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String9, address2, int9);
        testGetterLogic(ambulance0, "unitID", String9);
    }

    @Test
    public void testInstanceVariableAmbulanceLocationGetterLogic0() throws Exception {
        String String9 = "fzgmb";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int9 = 9;
        Object ambulance0 = Class.forName(ambulancePath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String9, address2, int9);
        testGetterLogic(ambulance0, "location", address2);
    }

    @Test
    public void testInstanceVariableAmbulanceDistanceToTargetGetterLogic0() throws Exception {
        String String9 = "fzgmb";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int9 = 9;
        Object ambulance0 = Class.forName(ambulancePath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String9, address2, int9);
        testGetterLogic(ambulance0, "distanceToTarget", 0);
    }

    @Test
    public void testInstanceVariableAmbulanceStepsPerCycleGetterLogic0() throws Exception {
        String String9 = "fzgmb";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int9 = 9;
        Object ambulance0 = Class.forName(ambulancePath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String9, address2, int9);
        testGetterLogic(ambulance0, "stepsPerCycle", 9);
    }

    @Test
    public void testInstanceVariableDiseaseControlUnitUnitIDGetterLogic0() throws Exception {
        String String3 = "ht";
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        int int0 = 0;
        Object diseaseControlUnit0 = Class.forName(diseaseControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String3, address0, int0);
        testGetterLogic(diseaseControlUnit0, "unitID", String3);
    }

    @Test
    public void testInstanceVariableDiseaseControlUnitLocationGetterLogic0() throws Exception {
        String String3 = "ht";
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        int int0 = 0;
        Object diseaseControlUnit0 = Class.forName(diseaseControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String3, address0, int0);
        testGetterLogic(diseaseControlUnit0, "location", address0);
    }

    @Test
    public void testInstanceVariableDiseaseControlUnitDistanceToTargetGetterLogic0() throws Exception {
        String String3 = "ht";
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        int int0 = 0;
        Object diseaseControlUnit0 = Class.forName(diseaseControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String3, address0, int0);
        testGetterLogic(diseaseControlUnit0, "distanceToTarget", 0);
    }

    @Test
    public void testInstanceVariableDiseaseControlUnitStepsPerCycleGetterLogic0() throws Exception {
        String String3 = "ht";
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        int int0 = 0;
        Object diseaseControlUnit0 = Class.forName(diseaseControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String3, address0, int0);
        testGetterLogic(diseaseControlUnit0, "stepsPerCycle", 0);
    }

    @Test
    public void testInstanceVariableCollapseStartCycleGetterLogic0() throws Exception {
        int int5 = 5;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object collapse0 = Class.forName(collapsePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int5, residentialBuilding2);
        testGetterLogic(collapse0, "startCycle", 5);
    }

    @Test
    public void testInstanceVariableCollapseTargetGetterLogic0() throws Exception {
        int int5 = 5;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object collapse0 = Class.forName(collapsePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int5, residentialBuilding2);
        testGetterLogic(collapse0, "target", residentialBuilding2);
    }

    @Test
    public void testInstanceVariableCollapseActiveGetterLogic0() throws Exception {
        int int5 = 5;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object collapse0 = Class.forName(collapsePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int5, residentialBuilding2);
        testGetterLogic(collapse0, "active", false);
    }

    @Test
    public void testInstanceVariableFireStartCycleGetterLogic0() throws Exception {
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object fire0 = Class.forName(firePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        testGetterLogic(fire0, "startCycle", 6);
    }

    @Test
    public void testInstanceVariableFireTargetGetterLogic0() throws Exception {
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object fire0 = Class.forName(firePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        testGetterLogic(fire0, "target", residentialBuilding2);
    }

    @Test
    public void testInstanceVariableFireActiveGetterLogic0() throws Exception {
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object fire0 = Class.forName(firePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        testGetterLogic(fire0, "active", false);
    }

    @Test
    public void testInstanceVariableGasLeakStartCycleGetterLogic0() throws Exception {
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object gasLeak0 = Class.forName(gasLeakPath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        testGetterLogic(gasLeak0, "startCycle", 6);
    }

    @Test
    public void testInstanceVariableGasLeakTargetGetterLogic0() throws Exception {
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object gasLeak0 = Class.forName(gasLeakPath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        testGetterLogic(gasLeak0, "target", residentialBuilding2);
    }

    @Test
    public void testInstanceVariableGasLeakActiveGetterLogic0() throws Exception {
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object gasLeak0 = Class.forName(gasLeakPath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        testGetterLogic(gasLeak0, "active", false);
    }

    @Test
    public void testInstanceVariableInfectionStartCycleGetterLogic0() throws Exception {
        int int5 = 5;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object infection0 = Class.forName(infectionPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int5, citizen0);
        testGetterLogic(infection0, "startCycle", 5);
    }

    @Test
    public void testInstanceVariableInfectionTargetGetterLogic0() throws Exception {
        int int5 = 5;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object infection0 = Class.forName(infectionPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int5, citizen0);
        testGetterLogic(infection0, "target", citizen0);
    }

    @Test
    public void testInstanceVariableInfectionActiveGetterLogic0() throws Exception {
        int int5 = 5;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object infection0 = Class.forName(infectionPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int5, citizen0);
        testGetterLogic(infection0, "active", false);
    }

    @Test
    public void testInstanceVariableInjuryStartCycleGetterLogic0() throws Exception {
        int int1 = 1;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object injury0 = Class.forName(injuryPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int1, citizen0);
        testGetterLogic(injury0, "startCycle", 1);
    }

    @Test
    public void testInstanceVariableInjuryTargetGetterLogic0() throws Exception {
        int int1 = 1;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object injury0 = Class.forName(injuryPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int1, citizen0);
        testGetterLogic(injury0, "target", citizen0);
    }

    @Test
    public void testInstanceVariableInjuryActiveGetterLogic0() throws Exception {
        int int1 = 1;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object injury0 = Class.forName(injuryPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int1, citizen0);
        testGetterLogic(injury0, "active", false);
    }

    @Test
    public void testInstanceVariableCitizenAgeSetterLogic0() throws Exception {
        int int5 = 5;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testSetterLogic(citizen0, "age", int5, int.class);
    }

    @Test
    public void testInstanceVariableCitizenHpSetterLogic0() throws Exception {
        int int1 = 1;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testSetterLogic(citizen0, "hp", int1, int.class);
    }

    @Test
    public void testInstanceVariableCitizenBloodLossSetterLogic0() throws Exception {
        int int0 = 0;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testSetterLogic(citizen0, "bloodLoss", int0, int.class);
    }

    @Test
    public void testInstanceVariableCitizenToxicitySetterLogic0() throws Exception {
        int int6 = 6;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        testSetterLogic(citizen0, "toxicity", int6, int.class);
    }

    @Test
    public void testInstanceVariableResidentialBuildingStructuralIntegritySetterLogic0() throws Exception {
        int int8 = 8;
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testSetterLogic(residentialBuilding0, "structuralIntegrity", int8, int.class);
    }

    @Test
    public void testInstanceVariableResidentialBuildingFireDamageSetterLogic0() throws Exception {
        int int4 = 4;
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testSetterLogic(residentialBuilding0, "fireDamage", int4, int.class);
    }

    @Test
    public void testInstanceVariableResidentialBuildingGasLevelSetterLogic0() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testSetterLogic(residentialBuilding0, "gasLevel", int6, int.class);
    }

    @Test
    public void testInstanceVariableResidentialBuildingFoundationDamageSetterLogic0() throws Exception {
        int int3 = 3;
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        testSetterLogic(residentialBuilding0, "foundationDamage", int3, int.class);
    }

    @Test
    public void testInstanceVariableEvacuatorDistToBaseSetterLogic0() throws Exception {
        int int3 = 3;
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        testSetterLogic(evacuator0, "distToBase", int3, int.class);
    }

    @Test
    public void testInstanceVariableEvacuatorDistanceToTargetSetterLogic0() throws Exception {
        int int5 = 5;
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        int int3 = 3;
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        testSetterLogic(evacuator0, "distanceToTarget", int5, int.class);
    }

    @Test
    public void testInstanceVariableFireTruckDistanceToTargetSetterLogic0() throws Exception {
        int int6 = 6;
        String String8 = "hid";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object fireTruck0 = Class.forName(fireTruckPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String8, address1, int0);
        testSetterLogic(fireTruck0, "distanceToTarget", int6, int.class);
    }

    @Test
    public void testInstanceVariableGasControlUnitDistanceToTargetSetterLogic0() throws Exception {
        int int2 = 2;
        String String4 = "kewp";
        int int8 = 8;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int7 = 7;
        Object gasControlUnit0 = Class.forName(gasControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String4, address2, int7);
        testSetterLogic(gasControlUnit0, "distanceToTarget", int2, int.class);
    }

    @Test
    public void testInstanceVariableAmbulanceDistanceToTargetSetterLogic0() throws Exception {
        int int0 = 0;
        String String9 = "fzgmb";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int9 = 9;
        Object ambulance0 = Class.forName(ambulancePath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String9, address2, int9);
        testSetterLogic(ambulance0, "distanceToTarget", int0, int.class);
    }

    @Test
    public void testInstanceVariableDiseaseControlUnitDistanceToTargetSetterLogic0() throws Exception {
        int int0 = 0;
        String String3 = "ht";
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object diseaseControlUnit0 = Class.forName(diseaseControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String3, address0, int0);
        testSetterLogic(diseaseControlUnit0, "distanceToTarget", int0, int.class);
    }

    @Test
    public void testInstanceVariableCollapseActiveSetterLogic0() throws Exception {
        boolean b1 = true;
        int int5 = 5;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object collapse0 = Class.forName(collapsePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int5, residentialBuilding2);
        testSetterLogic(collapse0, "active", b1, boolean.class);
    }

    @Test
    public void testInstanceVariableFireActiveSetterLogic0() throws Exception {
        boolean b2 = false;
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object fire0 = Class.forName(firePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        testSetterLogic(fire0, "active", b2, boolean.class);
    }

    @Test
    public void testInstanceVariableGasLeakActiveSetterLogic0() throws Exception {
        boolean b1 = true;
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object gasLeak0 = Class.forName(gasLeakPath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        testSetterLogic(gasLeak0, "active", b1, boolean.class);
    }

    @Test
    public void testInstanceVariableInfectionActiveSetterLogic0() throws Exception {
        boolean b1 = true;
        int int5 = 5;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object infection0 = Class.forName(infectionPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int5, citizen0);
        testSetterLogic(infection0, "active", b1, boolean.class);
    }

    @Test
    public void testInstanceVariableInjuryActiveSetterLogic0() throws Exception {
        boolean b2 = false;
        int int1 = 1;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object injury0 = Class.forName(injuryPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int1, citizen0);
        testSetterLogic(injury0, "active", b2, boolean.class);
    }

    @Test
    public void testConstructorAddressConstructor0Initialization() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        String[] names = {"x", "y"};
        Object[] values = {6, 6};
        testConstructorInitialization(address0, names, values);
    }

    @Test
    public void testConstructorCitizenConstructor0Initialization() throws Exception {
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        String[] names = {"state", "disaster", "name", "nationalID", "age", "hp", "bloodLoss", "toxicity", "location"};
        Object[] values = {Enum.valueOf((Class<Enum>) Class.forName(citizenStatePath), "SAFE"), null, String7, String4, 0, 100, 0, 0, address2};
        testConstructorInitialization(citizen0, names, values);
    }

    @Test
    public void testConstructorResidentialBuildingConstructor0Initialization() throws Exception {
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        Object residentialBuilding0 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address0);
        String[] names = {"location", "structuralIntegrity", "fireDamage", "gasLevel", "foundationDamage", "occupants", "disaster"};
        Object[] values = {address0, 100, 0, 0, 0, new ArrayList<>(), null};
        testConstructorInitialization(residentialBuilding0, names, values);
    }

    @Test
    public void testConstructorEvacuatorConstructor0Initialization() throws Exception {
        String String7 = "bnbijzoj";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        int int3 = 3;
        Object evacuator0 = Class.forName(evacuatorPath).getConstructor(String.class, Class.forName(addressPath), int.class, int.class).newInstance(String7, address1, int0, int3);
        String[] names = {"passengers", "maxCapacity", "distToBase", "unitID", "location", "target", "distanceToTarget", "stepsPerCycle"};
        Object[] values = {new ArrayList<>(), 3, 0, String7, address1, null, 0, 0};
        testConstructorInitialization(evacuator0, names, values);
    }

    @Test
    public void testConstructorFireTruckConstructor0Initialization() throws Exception {
        String String8 = "hid";
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object fireTruck0 = Class.forName(fireTruckPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String8, address1, int0);
        String[] names = {"unitID", "location", "target", "distanceToTarget", "stepsPerCycle"};
        Object[] values = {String8, address1, null, 0, 0};
        testConstructorInitialization(fireTruck0, names, values);
    }

    @Test
    public void testConstructorGasControlUnitConstructor0Initialization() throws Exception {
        String String4 = "kewp";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int7 = 7;
        Object gasControlUnit0 = Class.forName(gasControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String4, address2, int7);
        String[] names = {"unitID", "location", "target", "distanceToTarget", "stepsPerCycle"};
        Object[] values = {String4, address2, null, 0, 7};
        testConstructorInitialization(gasControlUnit0, names, values);
    }

    @Test
    public void testConstructorAmbulanceConstructor0Initialization() throws Exception {
        String String9 = "fzgmb";
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        int int9 = 9;
        Object ambulance0 = Class.forName(ambulancePath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String9, address2, int9);
        String[] names = {"healingAmount", "treatmentAmount", "unitID", "location", "target", "distanceToTarget", "stepsPerCycle"};
        Object[] values = {10, 10, String9, address2, null, 0, 9};
        testConstructorInitialization(ambulance0, names, values);
    }

    @Test
    public void testConstructorDiseaseControlUnitConstructor0Initialization() throws Exception {
        String String3 = "ht";
        int int6 = 6;
        Object address0 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int6, int6);
        int int0 = 0;
        Object diseaseControlUnit0 = Class.forName(diseaseControlUnitPath).getConstructor(String.class, Class.forName(addressPath), int.class).newInstance(String3, address0, int0);
        String[] names = {"healingAmount", "treatmentAmount", "unitID", "location", "target", "distanceToTarget", "stepsPerCycle"};
        Object[] values = {10, 10, String3, address0, null, 0, 0};
        testConstructorInitialization(diseaseControlUnit0, names, values);
    }

    @Test
    public void testConstructorCollapseConstructor0Initialization() throws Exception {
        int int5 = 5;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object collapse0 = Class.forName(collapsePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int5, residentialBuilding2);
        String[] names = {"startCycle", "target", "active"};
        Object[] values = {5, residentialBuilding2, false};
        testConstructorInitialization(collapse0, names, values);
    }

    @Test
    public void testConstructorFireConstructor0Initialization() throws Exception {
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object fire0 = Class.forName(firePath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        String[] names = {"startCycle", "target", "active"};
        Object[] values = {6, residentialBuilding2, false};
        testConstructorInitialization(fire0, names, values);
    }

    @Test
    public void testConstructorGasLeakConstructor0Initialization() throws Exception {
        int int6 = 6;
        int int0 = 0;
        int int2 = 2;
        Object address1 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int0, int2);
        Object residentialBuilding2 = Class.forName(residentialBuildingPath).getConstructor(Class.forName(addressPath)).newInstance(address1);
        Object gasLeak0 = Class.forName(gasLeakPath).getConstructor(int.class, Class.forName(residentialBuildingPath)).newInstance(int6, residentialBuilding2);
        String[] names = {"startCycle", "target", "active"};
        Object[] values = {6, residentialBuilding2, false};
        testConstructorInitialization(gasLeak0, names, values);
    }

    @Test
    public void testConstructorInfectionConstructor0Initialization() throws Exception {
        int int5 = 5;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object infection0 = Class.forName(infectionPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int5, citizen0);
        String[] names = {"startCycle", "target", "active"};
        Object[] values = {5, citizen0, false};
        testConstructorInitialization(infection0, names, values);
    }

    @Test
    public void testConstructorInjuryConstructor0Initialization() throws Exception {
        int int1 = 1;
        int int8 = 8;
        int int2 = 2;
        Object address2 = Class.forName(addressPath).getConstructor(int.class, int.class).newInstance(int8, int2);
        String String4 = "kewp";
        String String7 = "bnbijzoj";
        int int0 = 0;
        Object citizen0 = Class.forName(citizenPath).getConstructor(Class.forName(addressPath), String.class, String.class, int.class).newInstance(address2, String4, String7, int0);
        Object injury0 = Class.forName(injuryPath).getConstructor(int.class, Class.forName(citizenPath)).newInstance(int1, citizen0);
        String[] names = {"startCycle", "target", "active"};
        Object[] values = {1, citizen0, false};
        testConstructorInitialization(injury0, names, values);
    }


// ############################################# Helper methods

    public void testEnumValues(Class aClass, String[] value) throws ClassNotFoundException {
        for (int i = 0; i < value.length; i++) {
            try {
                Enum.valueOf((Class<Enum>) aClass, value[i]);
            } catch (IllegalArgumentException e) {
                fail(aClass.getSimpleName() + " enum can be " + value);
            }
        }
    }

    private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar) throws SecurityException {
        boolean thrown = false;
        try {
            aClass.getDeclaredField(varName);
        } catch (NoSuchFieldException e) {
            thrown = true;
        }
        if (implementedVar) {
            assertFalse(thrown, "There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".");
        } else {
            assertTrue(thrown, "The instance variable \"" + varName + "\" should not be declared in class " + aClass.getSimpleName() + ".");
        }
    }

    private void testInstanceVariableIsPrivate(Class aClass, String varName) throws NoSuchFieldException, SecurityException {
        Field f = aClass.getDeclaredField(varName);
        assertEquals(false, f.isAccessible(), "The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " should not be accessed outside that class.");
    }

    private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType, boolean writeVariable) {
        Method m = null;
        boolean found = true;
        try {
            m = aClass.getDeclaredMethod(methodName);
        } catch (Exception e) {
            found = false;
        }
        String varName = "";
        if (returnedType == boolean.class)
            varName = methodName.substring(2).toLowerCase();
        else
            varName = methodName.substring(3).toLowerCase();
        if (writeVariable) {
            assertTrue(found, "The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is a READ variable.");
            assertTrue(m.getReturnType() .isAssignableFrom(returnedType), "Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.");
        } else {
            assertFalse(found, "The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is not a READ variable.");
        }
    }

    private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType, boolean writeVariable) {
        Method[] methods = aClass.getDeclaredMethods();
        String varName = methodName.substring(3).toLowerCase();
        if (writeVariable) {
            assertTrue(containsMethodName(methods, methodName), "The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is a WRITE variable.");
        } else {
            assertFalse(containsMethodName(methods, methodName), "The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is not a WRITE variable.");
            return;
        }
        Method m = null;
        boolean found = true;
        try {
            m = aClass.getDeclaredMethod(methodName, inputType);
        } catch (NoSuchMethodException e) {
            found = false;
        }
        assertTrue(found, aClass.getSimpleName() + " class should have " + methodName + " method that takes one " + inputType.getSimpleName() + " parameter.");
        assertTrue(m.getReturnType().equals(Void.TYPE), "Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".");
    }

    private static boolean containsMethodName(Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName().equals(name))
                return true;
        }
        return false;
    }

    private void testConstructorExists(Class aClass, Class[] inputs) {
        boolean thrown = false;
        try {
            aClass.getConstructor(inputs);
        } catch (NoSuchMethodException e) {
            thrown = true;
        }
        if (inputs.length > 0) {
            String msg = "";
            int i = 0;
            do {
                msg += inputs[i].getSimpleName() + " and ";
                i++;
            } while (i < inputs.length);
            msg = msg.substring(0, msg.length() - 4);
            assertFalse(thrown, "Missing constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "") + " in " + aClass.getSimpleName() + " class.");
        } else
            assertFalse(thrown, "Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.");
    }

    private void testClassIsAbstract(Class aClass) {
        assertTrue(Modifier.isAbstract(aClass.getModifiers()), "You should not be able to create new instances from " + aClass.getSimpleName() + " class.");
    }

    private void testIsInterface(Class aClass) {
        assertEquals(aClass.isInterface(), true, aClass.getName() + " should be an Interface");
    }

    private void testIsEnum(Class aClass) {
        assertEquals(aClass.isEnum(), true, aClass.getName() + " should be an Enum");
    }

    private void testClassIsSubclass(Class subClass, Class superClass) {
        assertEquals(superClass, subClass.getSuperclass(), subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".");
    }

    private void testConstructorInitialization(Object createdObject, String[] names, Object[] values) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {
        for (int i = 0; i < names.length; i++) {
            Field f = null;
            Class curr = createdObject.getClass();
            String currName = names[i];
            Object currValue = values[i];
            while (f == null) {
                if (curr == Object.class)
                    fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + currName + "\".");
                try {
                    f = curr.getDeclaredField(currName);
                } catch (NoSuchFieldException e) {
                    curr = curr.getSuperclass();
                }
            }
            f.setAccessible(true);
            assertEquals(currValue, f.get(createdObject), "The constructor of the " + createdObject.getClass().getSimpleName() + " class should initialize the instance variable \"" + currName + "\" correctly.");
        }
    }

    private void testGetterLogic(Object createdObject, String name, Object value) throws Exception {
        Field f = null;
        Class curr = createdObject.getClass();
        while (f == null) {
            if (curr == Object.class)
                fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + name + "\".");
            try {
                f = curr.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                curr = curr.getSuperclass();
            }
        }
        f.setAccessible(true);
        f.set(createdObject, value);
        Character c = name.charAt(0);
        String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());
        if (value.getClass().equals(Boolean.class))
            methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());
        Method m = createdObject.getClass().getMethod(methodName);
        assertEquals(value, m.invoke(createdObject), "The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName() + " should return the correct value of variable \"" + name + "\".");
    }

    private void testSetterLogic(Object createdObject, String name, Object value, Class type) throws Exception {
        Field f = null;
        Class curr = createdObject.getClass();
        while (f == null) {

            if (curr == Object.class)
                fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + name + "\".");
            try {
                f = curr.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                curr = curr.getSuperclass();
            }
        }
        f.setAccessible(true);
        Character c = name.charAt(0);
        String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
        Method m = createdObject.getClass().getMethod(methodName, type);
        m.invoke(createdObject, value);
        assertEquals(value, f.get(createdObject), "The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName() + " should set the correct value of variable \"" + name + "\".");
    }

    private void testClassImplementsInterface(Class aClass, Class interfaceName) {
        Class[] interfaces = aClass.getInterfaces();
        boolean implement = false;
        for (Class i : interfaces) {
            if (i.toString().equals(interfaceName.toString()))
                implement = true;
        }
        assertTrue(implement,aClass.getSimpleName() + " class should implement " + interfaceName.getSimpleName() + " interface.");
    }
}
