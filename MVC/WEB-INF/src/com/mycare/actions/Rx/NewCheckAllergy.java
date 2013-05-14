package com.mycare.actions.Rx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewCheckAllergy {/*
	public void checkAllergies(){
	     List resultsList = new ArrayList();
	     List drugList1 = new ArrayList();
	     List allergenDrugs = new ArrayList();
	     List allergenClasses = new ArrayList();

	     Map drugIDMap = new HashMap();
	     Map compoundDrugIDMap = new HashMap();
	     Map compoundDrugAllergenIDMap = new HashMap();
	     Map allergenIDMap = new HashMap();
	     Map allergenDrugIDMap = new HashMap();

	     ArrayList compoundList = null;
	     ArrayList drugList = null;
	     ArrayList allergenList = null;
	     ArrayList allergenDrugList = null;
	     ArrayList allergenCompoundDrugList = null;

	     for (BaseDrug drug : screen.getDrugs()) {
	       if (drug.getConceptType() != CONCEPT_TYPE.CompoundDrug) {
	         if (((drug.getIsProspective() == prospectiveDrugsOnly) || (!prospectiveDrugsOnly)) && 
	           (!drugList1.contains(drug.getGenDrugID()))) {
	           drugList1.add(drug.getGenDrugID());
	   }

	         if (drugIDMap.containsKey(drug.getGenDrugID())) {
	           drugList = (ArrayList)drugIDMap.get(drug.getGenDrugID());
	   } else {
	           drugList = new ArrayList();
	           drugIDMap.put(drug.getGenDrugID(), drugList);
	   }

	         drugList.add(drug);
	 } else {
	         for (BaseDrug baseDrug : ((CompoundDrug)drug).getComponentDrugs()) {
	           if (((drug.getIsProspective() == prospectiveDrugsOnly) || 
	             (!prospectiveDrugsOnly)) && 
	             (!drugList1.contains(baseDrug.getGenDrugID()))) {
	             drugList1.add(baseDrug.getGenDrugID());
	     }

	           if (compoundDrugIDMap.containsKey(baseDrug.getGenDrugID())) {
	             compoundList = (ArrayList)compoundDrugIDMap.get(baseDrug.getGenDrugID());
	     } else {
	             compoundList = new ArrayList();
	             compoundDrugIDMap.put(baseDrug.getGenDrugID(), compoundList);
	     }

	           if (!compoundList.contains((CompoundDrug)drug)) {
	             compoundList.add((CompoundDrug)drug);
	     }
	   }
	 }

	}

	     for (BaseAllergen baseAllergen : screen.getAllergies()) {
	       if (baseAllergen.getConceptType() == CONCEPT_TYPE.AllergyClass) {
	         if (!allergenClasses.contains(Integer.valueOf(((AllergyClass)baseAllergen).getAllergyClassID()))) {
	           allergenClasses.add(Integer.valueOf(((AllergyClass)baseAllergen).getAllergyClassID()).toString());
	   }
	         if (allergenIDMap.containsKey(Integer.valueOf(((AllergyClass)baseAllergen).getAllergyClassID()))) {
	           allergenList = (ArrayList)allergenIDMap.get(Integer.valueOf(((AllergyClass)baseAllergen).getAllergyClassID()));
	   } else {
	           allergenList = new ArrayList();
	           allergenIDMap.put(Integer.valueOf(((AllergyClass)baseAllergen).getAllergyClassID()), allergenList);
	   }

	         allergenList.add(baseAllergen);
	 }
	       else if (baseAllergen.getConceptType() != CONCEPT_TYPE.CompoundDrug) {
	         if (!allergenDrugs.contains(((BaseDrug)baseAllergen).getGenDrugID())) {
	           allergenDrugs.add(((BaseDrug)baseAllergen).getGenDrugID());
	   }
	         if (allergenDrugIDMap.containsKey(((BaseDrug)baseAllergen).getGenDrugID())) {
	           allergenDrugList = (ArrayList)allergenDrugIDMap.get(((BaseDrug)baseAllergen).getGenDrugID());
	   } else {
	           allergenDrugList = new ArrayList();
	           allergenDrugIDMap.put(((BaseDrug)baseAllergen).getGenDrugID(), allergenDrugList);
	   }

	         allergenDrugList.add((BaseDrug)baseAllergen);
	 }
	 else {
	         for (BaseDrug baseDrug : ((CompoundDrug)baseAllergen).getComponentDrugs()) {
	           if (!allergenDrugs.contains(baseDrug.getGenDrugID())) {
	             allergenDrugs.add(baseDrug.getGenDrugID());
	     }
	           if (compoundDrugAllergenIDMap.containsKey(baseDrug.getGenDrugID())) {
	             allergenCompoundDrugList = (ArrayList)compoundDrugAllergenIDMap.get(baseDrug.getGenDrugID());
	     } else {
	             allergenCompoundDrugList = new ArrayList();
	             compoundDrugAllergenIDMap.put(baseDrug.getGenDrugID(), allergenCompoundDrugList);
	     }

	           if (!allergenCompoundDrugList.contains((CompoundDrug)baseAllergen)) {
	             allergenCompoundDrugList.add((CompoundDrug)baseAllergen);
	     }
	   }
	 }

	}

	     if ((drugList1.size() > 0) && ((allergenDrugs.size() > 0) || (allergenClasses.size() > 0))) {
	       HashMap matchTypes = new HashMap();
	       matchTypes = getAllergyMatchTypeAll();

	       if (matchTypes == null) {
	         throw new Throwable("Unable to load ALLERGY_MATCH_TYPE table.");
	 }
	       String[] drugListStringArray = (String[])drugList1.toArray(new String[0]);
	       String[] allergenDrugListStringArray = (String[])allergenDrugs.toArray(new String[0]);
	       String[] allergenClassesStringArray = (String[])allergenClasses.toArray(new String[0]);

	       DrugAllergyResult drugAllergyResult = null;

	       ResultSet rsDRUGALLERGY_INGREDIENT = null;
	       ResultSet rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT = null;
	       ResultSet rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT = null;
	       ResultSet rsDRUGALLERGY_CLASS_XREF_HIT = null;
	       ResultSet rsDRUGALLERGY_DRUG_XREF_HIT = null;
	       int hitType = 0;

	       String sql = null;
	       Connection conn = getConnection();
	       PreparedStatement ps0 = null;
	       PreparedStatement ps1 = null;
	       PreparedStatement ps2 = null;
	       PreparedStatement ps3 = null;
	       PreparedStatement ps4 = null;
	 try
	 {
	   String drugID2;
	   String HitIngredID;
	   Object HitIngredName;
	         if ((allergenDrugListStringArray != null) && (allergenDrugListStringArray.length > 0))
	   {
	           sql = MessageFormat.format(
	             getSQL("SCREENINGCONTEXT.DRUGALLERGY_INGREDIENT"), new Object[] { 
	             makePlaceholders(drugListStringArray), 
	             makePlaceholders(allergenDrugListStringArray) });

	           ps0 = conn.prepareStatement(sql);
	           int x = 1;
	           for (String s : drugListStringArray)
	             ps0.setString(x++, s);
	           for (String s : allergenDrugListStringArray) {
	             ps0.setString(x++, s);
	     }
	           rsDRUGALLERGY_INGREDIENT = ps0.executeQuery();

	           if (rsDRUGALLERGY_INGREDIENT != null) {
	             while (rsDRUGALLERGY_INGREDIENT.next())
	       {
	               String drugID1 = rsDRUGALLERGY_INGREDIENT.getString("DRUGID1") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INGREDIENT.getString("DRUGID1");
	               drugID2 = rsDRUGALLERGY_INGREDIENT.getString("DRUGID2") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INGREDIENT.getString("DRUGID2");
	               HitIngredID = rsDRUGALLERGY_INGREDIENT.getString("INGREDIENT_DRUG_ID") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INGREDIENT.getString("INGREDIENT_DRUG_ID");
	               HitIngredName = rsDRUGALLERGY_INGREDIENT.getString("GENERIC_NAME") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INGREDIENT.getString("GENERIC_NAME");

	               ArrayList drugs1 = null;
	               ArrayList drugs2 = null;

	               ArrayList compDrugs1 = null;
	               ArrayList compDrugs2 = null;

	               boolean isDrug1 = drugIDMap.containsKey(drugID1);
	               if (isDrug1) {
	                 drugs1 = (ArrayList)drugIDMap.get(drugID1);
	         }
	               boolean isDrug2 = allergenDrugIDMap.containsKey(drugID2);
	               if (isDrug2) {
	                 drugs2 = (ArrayList)allergenDrugIDMap.get(drugID2);
	         }
	               boolean isComp1 = compoundDrugIDMap.containsKey(drugID1);
	               if (isComp1) {
	                 compDrugs1 = (ArrayList)compoundDrugIDMap.get(drugID1);
	         }
	               boolean isComp2 = compoundDrugAllergenIDMap.containsKey(drugID2);
	               if (isComp2) {
	                 compDrugs2 = (ArrayList)compoundDrugAllergenIDMap.get(drugID2);
	         }
	               boolean bDrug = false;
	               boolean bAllergen = false;
	               ArrayList baseDrugList = null;
	               ArrayList baseAllergenList = null;

	               for (int cnt = 0; cnt < 4; cnt++) {
	                 switch (cnt) {
	           case 0:
	                   bDrug = isDrug1;
	                   bAllergen = isDrug2;
	                   baseDrugList = drugs1;

	                   if (drugs2 == null) break;
	                   baseAllergenList = new ArrayList(drugs2);

	                   break;
	           case 1:
	                   bDrug = isDrug1;
	                   bAllergen = isComp2;
	                   baseDrugList = drugs1;

	                   if (compDrugs2 == null) break;
	                   baseAllergenList = new ArrayList(compDrugs2);

	                   break;
	           case 2:
	                   bDrug = isComp1;
	                   bAllergen = isComp2;

	                   if (compDrugs1 != null) {
	                     baseDrugList = new ArrayList(compDrugs1);
	             }
	                   if (compDrugs2 == null) break;
	                   baseAllergenList = new ArrayList(compDrugs2);

	                   break;
	           case 3:
	                   bDrug = isComp1;
	                   bAllergen = isDrug2;

	                   if (compDrugs1 != null) {
	                     baseDrugList = new ArrayList(compDrugs1);
	             }
	                   if (drugs2 == null) break;
	                   baseAllergenList = new ArrayList(drugs2);
	           }

	                 if ((bDrug) && (bAllergen)) {
	                   if (drugID1.equals(HitIngredID)) {
	                     if (drugID2.equals(HitIngredID))
	                       hitType = 1;
	               else
	                       hitType = 11;
	             }
	                   else if (drugID2.equals(HitIngredID))
	                     hitType = 6;
	             else {
	                     hitType = 16;
	             }

	                   AllergyMatchType row = (AllergyMatchType)matchTypes.get(Integer.valueOf(hitType));
	                   String matchTypeDesc = row.getMatchTypeDescription();

	                   for (BaseDrug baseDrug1 : baseDrugList) {
	                     for (BaseDrug baseDrug2 : baseAllergenList) {
	                       drugAllergyResult = new DrugAllergyResult();
	                       drugAllergyResult.setDrug(baseDrug1);
	                       drugAllergyResult.setAllergen(baseDrug2);

	                       drugAllergyResult.setMatchTypeID(hitType);
	                       drugAllergyResult.setMatchTypeDescription(matchTypeDesc);
	                       drugAllergyResult.setAllergySeverity(baseDrug2.getAllergySeverity());
	                       drugAllergyResult.setReaction(baseDrug2.getReaction());

	                       String parentName = baseDrug1.getGenericName();

	                       if (baseDrug1.getConceptType() == CONCEPT_TYPE.CompoundDrug) {
	                         parentName = ((CompoundDrug)baseDrug1).getDrugName();
	                 }
	                       String template = row.getTemplateText();

	                       if ((hitType == 1) || (hitType == 11))
	                         template = template.replace("{index_parent_drug}", parentName);
	                 else {
	                         template = template.replace("{index_child_drug}", (CharSequence)HitIngredName);
	                 }
	                       drugAllergyResult.setAllergyMessage(template);
	                       resultsList.add(drugAllergyResult);
	               }
	             }
	           }
	         }
	       }

	     }

	           if (rsDRUGALLERGY_INGREDIENT != null)
	             rsDRUGALLERGY_INGREDIENT.close();
	   }
	   String drugID2;
	   String HitIngredID1;
	   Object HitIngredName1;
	   boolean bDrug;
	   ArrayList baseDrugList;
	   String drugParentName;
	   String populateTemp;
	         if ((allergenDrugListStringArray != null) && (allergenDrugListStringArray.length > 0))
	   {
	           sql = MessageFormat.format(
	             getSQL("SCREENINGCONTEXT.DRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT"), new Object[] { 
	             makePlaceholders(drugListStringArray), 
	             makePlaceholders(allergenDrugListStringArray) });

	           ps1 = conn.prepareStatement(sql);
	           int x = 1;
	           String str9 = (HitIngredName = drugListStringArray).length; for (String str1 = 0; str1 < str9; str1++) { String s = HitIngredName[str1];
	             ps1.setString(x++, s); }
	           String str10 = (HitIngredName = allergenDrugListStringArray).length; for (String str2 = 0; str2 < str10; str2++) { String s = HitIngredName[str2];
	             ps1.setString(x++, s);
	     }
	           rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT = ps1.executeQuery();

	           if (rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT != null) {
	             while (rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.next()) {
	               String drugID1 = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("DRUGID1") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("DRUGID1");
	               drugID2 = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("DRUGID2") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("DRUGID2");
	               HitIngredID1 = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("IngredDrugID1") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("IngredDrugID1");
	               HitIngredName1 = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("GENERIC_NAME1") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("GENERIC_NAME1");
	               String HitIngredID2 = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("IngredDrugID2") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("IngredDrugID2");
	               String HitIngredName2 = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("GENERIC_NAME2") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("GENERIC_NAME2");
	               int classID = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getInt("ALLERGY_CLASS_ID");
	               String className = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("CLASS_DESCRIPTION") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("CLASS_DESCRIPTION");
	               String classNamePlural = rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("CLASS_DESCRIPTION_PLURAL") == null ? 
	                 "" : 
	                 rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.getString("CLASS_DESCRIPTION_PLURAL");

	               ArrayList drugs1 = null;
	               ArrayList drugs2 = null;

	               ArrayList compDrugs1 = null;
	               ArrayList compDrugs2 = null;

	               boolean isDrug1 = drugIDMap.containsKey(drugID1);
	               if (isDrug1) {
	                 drugs1 = (ArrayList)drugIDMap.get(drugID1);
	         }
	               boolean isDrug2 = allergenDrugIDMap.containsKey(drugID2);
	               if (isDrug2) {
	                 drugs2 = (ArrayList)allergenDrugIDMap.get(drugID2);
	         }
	               boolean isComp1 = compoundDrugIDMap.containsKey(drugID1);
	               if (isComp1) {
	                 compDrugs1 = (ArrayList)compoundDrugIDMap.get(drugID1);
	         }
	               boolean isComp2 = compoundDrugAllergenIDMap.containsKey(drugID2);
	               if (isComp2) {
	                 compDrugs2 = (ArrayList)compoundDrugAllergenIDMap.get(drugID2);
	         }
	               bDrug = false;
	               boolean bAllergen = false;
	               baseDrugList = null;
	               ArrayList baseAllergenList = null;

	               for (int cnt = 0; cnt < 4; cnt++) {
	                 switch (cnt) {
	           case 0:
	                   bDrug = isDrug1;
	                   bAllergen = isDrug2;
	                   baseDrugList = drugs1;
	                   baseAllergenList = drugs2;
	                   break;
	           case 1:
	                   bDrug = isDrug1;
	                   bAllergen = isComp2;
	                   baseDrugList = drugs1;

	                   if (compDrugs2 == null) break;
	                   baseAllergenList = new ArrayList(compDrugs2);

	                   break;
	           case 2:
	                   bDrug = isComp1;
	                   bAllergen = isComp2;

	                   if (compDrugs1 != null) {
	                     baseDrugList = new ArrayList(compDrugs1);
	             }
	                   if (compDrugs2 == null) break;
	                   baseAllergenList = new ArrayList(compDrugs2);

	                   break;
	           case 3:
	                   bDrug = isComp1;
	                   bAllergen = isDrug2;

	                   if (compDrugs1 != null) {
	                     baseDrugList = new ArrayList(compDrugs1);
	             }
	                   baseAllergenList = drugs2;
	           }

	                 if ((bDrug) && (bAllergen)) {
	                   if (drugID1.equals(HitIngredID1)) {
	                     if (drugID2.equals(HitIngredID2))
	                       hitType = 2;
	               else
	                       hitType = 12;
	             }
	                   else if (drugID2.equals(HitIngredID2))
	                     hitType = 7;
	             else {
	                     hitType = 17;
	             }

	                   AllergyMatchType row = (AllergyMatchType)matchTypes.get(Integer.valueOf(hitType));
	                   String matchTypeDesc = row.getMatchTypeDescription();
	                   String template = getAllergyTemplate(classID, hitType);

	                   if (template == null) {
	                     template = row.getTemplateText();
	             }

	                   for (BaseDrug baseDrug1 : baseDrugList) {
	                     for (BaseDrug baseDrug2 : baseAllergenList)
	               {
	                       drugAllergyResult = new DrugAllergyResult();
	                       drugAllergyResult.setDrug(baseDrug1);
	                       drugAllergyResult.setAllergen(baseDrug2);
	                       drugAllergyResult.setMatchTypeID(hitType);
	                       drugAllergyResult.setMatchTypeDescription(matchTypeDesc);
	                       drugAllergyResult.setAllergySeverity(baseDrug2.getAllergySeverity());
	                       drugAllergyResult.setReaction(baseDrug2.getReaction());

	                       drugAllergyResult.setClassID(classID);
	                       drugAllergyResult.setClassDescription(className);
	                       drugAllergyResult.setClassDescriptionPlural(classNamePlural);

	                       drugParentName = baseDrug1.getGenericName();
	                       String allergenParentName = baseDrug2.getGenericName();

	                       if (baseDrug1.getConceptType() == CONCEPT_TYPE.CompoundDrug) {
	                         drugParentName = ((CompoundDrug)baseDrug1).getDrugName();
	                 }
	                       if (baseDrug2.getConceptType() == CONCEPT_TYPE.CompoundDrug) {
	                         allergenParentName = ((CompoundDrug)baseDrug2).getDrugName();
	                 }
	                       populateTemp = template;
	                       populateTemp = populateTemp.replace("{index_parent_drug}", drugParentName);
	                       populateTemp = populateTemp.replace("{index_child_drug}", (CharSequence)HitIngredName1);
	                       populateTemp = populateTemp.replace("{alr_parent_drug}", allergenParentName);
	                       populateTemp = populateTemp.replace("{alr_child_drug}", HitIngredName2);
	                       populateTemp = populateTemp.replace("{index_class}", className);
	                       populateTemp = populateTemp.replace("{alr_class}", className);
	                       populateTemp = populateTemp.replace("{alr_class_plural}", classNamePlural);
	                       drugAllergyResult.setAllergyMessage(populateTemp);
	                       resultsList.add(drugAllergyResult);
	               }
	             }
	           }
	         }
	       }
	     }

	           if (rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT != null) {
	             rsDRUGALLERGY_INDIRECT_ALLERGENCLASS_HIT.close();
	     }
	   }
	         if ((allergenClassesStringArray != null) && (allergenClassesStringArray.length > 0)) {
	           sql = MessageFormat.format(
	             getSQL("SCREENINGCONTEXT.DRUGALLERGY_DIRECT_ALLERGENCLASS_HIT"), new Object[] { 
	             makePlaceholders(drugListStringArray), 
	             makePlaceholders(allergenClassesStringArray) });

	           ps2 = conn.prepareStatement(sql);
	           int x = 1;
	           String str11 = (HitIngredName1 = drugListStringArray).length; for (String str3 = 0; str3 < str11; str3++) { String s = HitIngredName1[str3];
	             ps2.setString(x++, s); }
	           String str12 = (HitIngredName1 = allergenClassesStringArray).length; for (String str4 = 0; str4 < str12; str4++) { String s = HitIngredName1[str4];
	             ps2.setString(x++, s);
	     }
	           rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT = ps2.executeQuery();
	   }
	   String HitIngredID;
	   String HitIngredName;
	   Object className;
	         if (rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT != null) {
	           while (rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.next()) {
	             String drugID1 = rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("DRUGID1") == null ? 
	               "" : 
	               rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("DRUGID1");
	             int allergyClassID = rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getInt("ALLERGY_CLASS_ID");
	             HitIngredID = rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("INGREDIENT_DRUG_ID") == null ? 
	               "" : 
	               rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("INGREDIENT_DRUG_ID");
	             HitIngredName = rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("GENERIC_NAME") == null ? 
	               "" : 
	               rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("GENERIC_NAME");
	             className = rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("CLASS_DESCRIPTION") == null ? 
	               "" : 
	               rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("CLASS_DESCRIPTION");
	             String classNamePlural = rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("PLURAL_NAME") == null ? 
	               "" : 
	               rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.getString("PLURAL_NAME");

	             ArrayList drugs1 = null;
	             ArrayList allergenClassList = null;

	             boolean isAllergyClass = allergenIDMap.containsKey(Integer.valueOf(allergyClassID));
	             if (isAllergyClass) {
	               allergenClassList = (ArrayList)allergenIDMap.get(Integer.valueOf(allergyClassID));
	       }
	             ArrayList compDrugs1 = null;

	             boolean isDrug1 = drugIDMap.containsKey(drugID1);
	             if (isDrug1) {
	               drugs1 = (ArrayList)drugIDMap.get(drugID1);
	       }
	             boolean isComp1 = compoundDrugIDMap.containsKey(drugID1);
	             if (isComp1) {
	               compDrugs1 = (ArrayList)compoundDrugIDMap.get(drugID1);
	       }
	             boolean bDrug = false;
	             ArrayList baseDrugList = null;

	             for (int cnt = 0; cnt < 2; cnt++) {
	               switch (cnt) {
	         case 0:
	                 bDrug = isDrug1;
	                 baseDrugList = drugs1;
	                 break;
	         case 1:
	                 bDrug = isComp1;

	                 if (compDrugs1 == null) break;
	                 baseDrugList = new ArrayList(compDrugs1);
	         }

	               if ((bDrug) && (isAllergyClass)) {
	                 if (drugID1.equals(HitIngredID))
	                   hitType = 4;
	           else {
	                   hitType = 9;
	           }

	                 AllergyMatchType row = (AllergyMatchType)matchTypes.get(Integer.valueOf(hitType));
	                 String matchTypeDesc = row.getMatchTypeDescription();

	                 for (BaseDrug baseDrug1 : baseDrugList) {
	                   for (BaseAllergen allergryClass : allergenClassList)
	             {
	                     drugAllergyResult = new DrugAllergyResult();
	                     drugAllergyResult.setDrug(baseDrug1);
	                     drugAllergyResult.setAllergen(allergryClass);
	                     drugAllergyResult.setMatchTypeID(hitType);
	                     drugAllergyResult.setMatchTypeDescription(matchTypeDesc);
	                     drugAllergyResult.setAllergySeverity(allergryClass.getAllergySeverity());
	                     drugAllergyResult.setReaction(allergryClass.getReaction());
	                     drugAllergyResult.setClassID(allergyClassID);
	                     drugAllergyResult.setClassDescription((String)className);
	                     drugAllergyResult.setClassDescriptionPlural(classNamePlural);

	                     String drugParentName = baseDrug1.getGenericName();

	                     if (baseDrug1.getConceptType() == CONCEPT_TYPE.CompoundDrug) {
	                       drugParentName = ((CompoundDrug)baseDrug1).getDrugName();
	               }
	                     String template = row.getTemplateText();

	                     if (hitType == 4) {
	                       template = template.replace("{index_parent_drug}", drugParentName);
	                       template = template.replace("{index_class}", (CharSequence)className);
	                       template = template.replace("{alr_class_plural}", classNamePlural);
	               } else {
	                       template = template.replace("{index_parent_drug}", drugParentName);
	                       template = template.replace("{index_child_drug}", HitIngredName);
	                       template = template.replace("{alr_class}", (CharSequence)className);
	                       template = template.replace("{alr_class_plural}", classNamePlural);
	               }

	                     drugAllergyResult.setAllergyMessage(template);
	                     resultsList.add(drugAllergyResult);
	             }
	           }
	         }
	       }
	     }
	   }

	         if (rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT != null) {
	           rsDRUGALLERGY_DIRECT_ALLERGENCLASS_HIT.close();
	   }
	         if ((allergenDrugListStringArray != null) && (allergenDrugListStringArray.length > 0)) {
	           sql = MessageFormat.format(
	             getSQL("SCREENINGCONTEXT.DRUGALLERGY_CLASS_XREF_HIT"), new Object[] { 
	             makePlaceholders(drugListStringArray), 
	             makePlaceholders(allergenDrugListStringArray) });

	           ps3 = conn.prepareStatement(sql);
	           int x = 1;
	           String str13 = (className = drugListStringArray).length; for (String str5 = 0; str5 < str13; str5++) { String s = className[str5];
	             ps3.setString(x++, s); }
	           String str14 = (className = allergenDrugListStringArray).length; for (String str6 = 0; str6 < str14; str6++) { String s = className[str6];
	             ps3.setString(x++, s);
	     }
	           rsDRUGALLERGY_CLASS_XREF_HIT = ps3.executeQuery();
	   }
	   String HitIngredID1;
	   String HitIngredName1;
	   Object HitIngredID2;
	   ArrayList baseAllergenList;
	   AllergyMatchType row;
	         if (rsDRUGALLERGY_CLASS_XREF_HIT != null) {
	           while (rsDRUGALLERGY_CLASS_XREF_HIT.next()) {
	             String drugID1 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("DRUGID1") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("DRUGID1");
	             String drugID2 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("DRUGID2") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("DRUGID2");
	             HitIngredID1 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("IngredDrugID1") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("IngredDrugID1");
	             HitIngredName1 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("GENERIC_NAME1") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("GENERIC_NAME1");
	             HitIngredID2 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("IngredDrugID2") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("IngredDrugID2");
	             String HitIngredName2 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("GENERIC_NAME2") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("GENERIC_NAME2");

	             int classID1 = rsDRUGALLERGY_CLASS_XREF_HIT.getInt("ALLERGY_CLASS_ID1");
	             String className1 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("CLASS_DESCRIPTION1") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("CLASS_DESCRIPTION1");

	             String classNamePlural1 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("CLASS_DESCRIPTION_PLURAL1") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("CLASS_DESCRIPTION_PLURAL1");
	             String className2 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("CLASS_DESCRIPTION2") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("CLASS_DESCRIPTION2");
	             String classNamePlural2 = rsDRUGALLERGY_CLASS_XREF_HIT.getString("CLASS_DESCRIPTION_PLURAL2") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("CLASS_DESCRIPTION_PLURAL2");

	             int XRGroupID = rsDRUGALLERGY_CLASS_XREF_HIT.getInt("ALLERGY_XRGROUP_ID");
	             String XRGroupDesc = rsDRUGALLERGY_CLASS_XREF_HIT.getString("XRGROUP_DESCRIPTION") == null ? 
	               "" : 
	               rsDRUGALLERGY_CLASS_XREF_HIT.getString("XRGROUP_DESCRIPTION");

	             ArrayList drugs1 = null;
	             ArrayList drugs2 = null;

	             ArrayList compDrugs1 = null;
	             ArrayList compDrugs2 = null;

	             boolean isDrug1 = drugIDMap.containsKey(drugID1);
	             if (isDrug1) {
	               drugs1 = (ArrayList)drugIDMap.get(drugID1);
	       }
	             boolean isDrug2 = allergenDrugIDMap.containsKey(drugID2);
	             if (isDrug2) {
	               drugs2 = (ArrayList)allergenDrugIDMap.get(drugID2);
	       }
	             boolean isComp1 = compoundDrugIDMap.containsKey(drugID1);
	             if (isComp1) {
	               compDrugs1 = (ArrayList)compoundDrugIDMap.get(drugID1);
	       }
	             boolean isComp2 = compoundDrugAllergenIDMap.containsKey(drugID2);
	             if (isComp2) {
	               compDrugs2 = (ArrayList)compoundDrugAllergenIDMap.get(drugID2);
	       }
	             boolean bDrug = false;
	             boolean bAllergen = false;
	             ArrayList baseDrugList = null;
	             baseAllergenList = null;

	             for (int cnt = 0; cnt < 4; cnt++) {
	               switch (cnt) {
	         case 0:
	                 bDrug = isDrug1;
	                 bAllergen = isDrug2;
	                 baseDrugList = drugs1;
	                 baseAllergenList = drugs2;
	                 break;
	         case 1:
	                 bDrug = isDrug1;
	                 bAllergen = isComp2;
	                 baseDrugList = drugs1;

	                 if (compDrugs2 == null) break;
	                 baseAllergenList = new ArrayList(compDrugs2);

	                 break;
	         case 2:
	                 bDrug = isComp1;
	                 bAllergen = isComp2;

	                 if (compDrugs1 != null) {
	                   baseDrugList = new ArrayList(compDrugs1);
	           }
	                 if (compDrugs2 == null) break;
	                 baseAllergenList = new ArrayList(compDrugs2);

	                 break;
	         case 3:
	                 bDrug = isComp1;
	                 bAllergen = isDrug2;

	                 if (compDrugs1 != null) {
	                   baseDrugList = new ArrayList(compDrugs1);
	           }
	                 baseAllergenList = drugs2;
	         }

	               if ((!bDrug) || (!bAllergen))
	           continue;
	               if (drugID1.equals(HitIngredID1)) {
	                 if (drugID2.equals(HitIngredID2))
	                   hitType = 3;
	           else
	                   hitType = 13;
	         }
	               else if (drugID2.equals(HitIngredID2))
	                 hitType = 8;
	         else {
	                 hitType = 18;
	         }

	               row = (AllergyMatchType)matchTypes.get(Integer.valueOf(hitType));
	               String matchTypeDesc = row.getMatchTypeDescription();
	               String template = getAllergyTemplate(classID1, hitType);

	               if (template == null) {
	                 template = row.getTemplateText();
	         }

	               for (BaseDrug baseDrug1 : baseDrugList) {
	                 for (BaseDrug baseDrug2 : baseAllergenList) {
	                   drugAllergyResult = new DrugAllergyResult();
	                   drugAllergyResult.setDrug(baseDrug1);
	                   drugAllergyResult.setAllergen(baseDrug2);
	                   drugAllergyResult.setMatchTypeID(hitType);
	                   drugAllergyResult.setMatchTypeDescription(matchTypeDesc);
	                   drugAllergyResult.setAllergySeverity(baseDrug2.getAllergySeverity());
	                   drugAllergyResult.setReaction(baseDrug2.getReaction());
	                   drugAllergyResult.setAllergyXRGroupID(XRGroupID);
	                   drugAllergyResult.setAllergyXRGroupDescription(XRGroupDesc);

	                   String allergenParentName = baseDrug2.getGenericName();
	                   String drugParentName = baseDrug1.getGenericName();
	                   if (baseDrug1.getConceptType() == CONCEPT_TYPE.CompoundDrug) {
	                     drugParentName = ((CompoundDrug)baseDrug1).getDrugName();
	             }
	                   if (baseDrug2.getConceptType() == CONCEPT_TYPE.CompoundDrug) {
	                     allergenParentName = ((CompoundDrug)baseDrug2).getDrugName();
	             }
	                   String populateTemp = template;
	                   populateTemp = populateTemp.replace("{index_parent_drug}", drugParentName);
	                   populateTemp = populateTemp.replace("{index_child_drug}", HitIngredName1);
	                   populateTemp = populateTemp.replace("{alr_parent_drug}", allergenParentName);
	                   populateTemp = populateTemp.replace("{alr_child_drug}", HitIngredName2);
	                   populateTemp = populateTemp.replace("{index_class}", className1);
	                   populateTemp = populateTemp.replace("{index_class_plural}", classNamePlural1);
	                   populateTemp = populateTemp.replace("{alr_class}", className2);
	                   populateTemp = populateTemp.replace("{alr_class_plural}", classNamePlural2);
	                   drugAllergyResult.setAllergyMessage(populateTemp);
	                   resultsList.add(drugAllergyResult);
	           }
	         }
	       }
	     }

	   }

	         if (rsDRUGALLERGY_CLASS_XREF_HIT != null) {
	           rsDRUGALLERGY_CLASS_XREF_HIT.close();
	   }
	         if ((allergenClassesStringArray != null) && (allergenClassesStringArray.length > 0)) {
	           sql = MessageFormat.format(
	             getSQL("SCREENINGCONTEXT.DRUGALLERGY_DRUG_XREF_HIT"), new Object[] { 
	             makePlaceholders(drugListStringArray), 
	             makePlaceholders(allergenClassesStringArray) });

	           ps4 = conn.prepareStatement(sql);
	           int x = 1;
	           String str15 = (HitIngredID2 = drugListStringArray).length; for (String str7 = 0; str7 < str15; str7++) { String s = HitIngredID2[str7];
	             ps4.setString(x++, s); }
	           String str16 = (HitIngredID2 = allergenClassesStringArray).length; for (String str8 = 0; str8 < str16; str8++) { String s = HitIngredID2[str8];
	             ps4.setString(x++, s); }
	           rsDRUGALLERGY_DRUG_XREF_HIT = ps4.executeQuery();
	   }

	         if (rsDRUGALLERGY_DRUG_XREF_HIT != null) {
	           while (rsDRUGALLERGY_DRUG_XREF_HIT.next())
	     {
	             String drugID1 = rsDRUGALLERGY_DRUG_XREF_HIT.getString("DRUG_ID1") == null ? 
	               "" : 
	               rsDRUGALLERGY_DRUG_XREF_HIT.getString("DRUG_ID1");
	             String HitIngredID1 = rsDRUGALLERGY_DRUG_XREF_HIT.getString("INGREDIENT_DRUG_ID") == null ? 
	               "" : 
	               rsDRUGALLERGY_DRUG_XREF_HIT.getString("INGREDIENT_DRUG_ID");
	             String HitIngredName1 = rsDRUGALLERGY_DRUG_XREF_HIT.getString("GENERIC_NAME") == null ? 
	               "" : 
	               rsDRUGALLERGY_DRUG_XREF_HIT.getString("GENERIC_NAME");

	             int classID1 = rsDRUGALLERGY_DRUG_XREF_HIT.getInt("ALLERGY_CLASS_ID1");
	             String className1 = rsDRUGALLERGY_DRUG_XREF_HIT.getString("CLASS_DESCRIPTION1") == null ? 
	               "" : 
	               rsDRUGALLERGY_DRUG_XREF_HIT.getString("CLASS_DESCRIPTION1");
	             String classNamePlural1 = rsDRUGALLERGY_DRUG_XREF_HIT.getString("CLASS_DESCRIPTION_PLURAL1") == null ? 
	               "" : 
	               rsDRUGALLERGY_DRUG_XREF_HIT.getString("CLASS_DESCRIPTION_PLURAL1");

	             int classID2 = rsDRUGALLERGY_DRUG_XREF_HIT.getInt("ALLERGY_CLASS_ID2");
	             String className2 = rsDRUGALLERGY_DRUG_XREF_HIT.getString("CLASS_DESCRIPTION2") == null ? 
	               "" : 
	               rsDRUGALLERGY_DRUG_XREF_HIT.getString("CLASS_DESCRIPTION2");
	             String classNamePlural2 = rsDRUGALLERGY_DRUG_XREF_HIT.getString("CLASS_DESCRIPTION_PLURAL2") == null ? 
	               "" : 
	               rsDRUGALLERGY_DRUG_XREF_HIT.getString("CLASS_DESCRIPTION_PLURAL2");

	             int XRGroupID = rsDRUGALLERGY_DRUG_XREF_HIT.getInt("ALLERGY_XRGROUP_ID");
	             String XRGroupDesc = rsDRUGALLERGY_DRUG_XREF_HIT.getString("DISPLAY_NAME") == null ? 
	               "" : 
	               rsDRUGALLERGY_DRUG_XREF_HIT.getString("DISPLAY_NAME");

	             ArrayList drugs1 = null;
	             ArrayList allergenClassList = null;

	             boolean isAllergyClass = allergenIDMap.containsKey(Integer.valueOf(classID2));
	             if (isAllergyClass) {
	               allergenClassList = (ArrayList)allergenIDMap.get(Integer.valueOf(classID2));
	       }
	             ArrayList compDrugs1 = null;

	             boolean isDrug1 = drugIDMap.containsKey(drugID1);
	             if (isDrug1) {
	               drugs1 = (ArrayList)drugIDMap.get(drugID1);
	       }
	             boolean isComp1 = compoundDrugIDMap.containsKey(drugID1);
	             if (isComp1) {
	               compDrugs1 = (ArrayList)compoundDrugIDMap.get(drugID1);
	       }
	             boolean bDrug = false;
	             ArrayList baseDrugList = null;

	             for (int cnt = 0; cnt < 2; cnt++) {
	               switch (cnt) {
	         case 0:
	                 bDrug = isDrug1;
	                 baseDrugList = drugs1;
	                 break;
	         case 1:
	                 bDrug = isComp1;

	                 if (compDrugs1 == null) break;
	                 baseDrugList = new ArrayList(compDrugs1);
	         }

	               if ((bDrug) && (isAllergyClass)) {
	                 if (drugID1.equals(HitIngredID1))
	                   hitType = 5;
	           else {
	                   hitType = 10;
	           }

	                 AllergyMatchType row = (AllergyMatchType)matchTypes.get(Integer.valueOf(hitType));
	                 String matchTypeDesc = row.getMatchTypeDescription();
	                 String template = getAllergyTemplate(classID1, hitType);

	                 if (template == null) {
	                   template = row.getTemplateText();
	           }

	                 for (BaseDrug baseDrug1 : baseDrugList) {
	                   for (BaseAllergen allergryClass : allergenClassList) {
	                     drugAllergyResult = new DrugAllergyResult();
	                     drugAllergyResult.setDrug(baseDrug1);
	                     drugAllergyResult.setAllergen(allergryClass);
	                     drugAllergyResult.setMatchTypeID(hitType);
	                     drugAllergyResult.setMatchTypeDescription(matchTypeDesc);
	                     drugAllergyResult.setAllergySeverity(allergryClass.getAllergySeverity());
	                     drugAllergyResult.setReaction(allergryClass.getReaction());
	                     drugAllergyResult.setAllergyXRGroupID(XRGroupID);
	                     drugAllergyResult.setAllergyXRGroupDescription(XRGroupDesc);

	                     String drugParentName = baseDrug1.getGenericName();
	                     if (baseDrug1.getConceptType() == CONCEPT_TYPE.CompoundDrug) {
	                       drugParentName = ((CompoundDrug)baseDrug1).getDrugName();
	               }
	                     String populateTemp = template;
	                     populateTemp = populateTemp.replace("{index_parent_drug}", drugParentName);
	                     populateTemp = populateTemp.replace("{index_child_drug}", HitIngredName1);
	                     populateTemp = populateTemp.replace("{index_class}", className1);
	                     populateTemp = populateTemp.replace("{index_class_plural}", classNamePlural1);
	                     populateTemp = populateTemp.replace("{alr_class}", className2);
	                     populateTemp = populateTemp.replace("{alr_class_plural}", classNamePlural2);
	                    drugAllergyResult.setAllergyMessage(populateTemp);
	                    resultsList.add(drugAllergyResult);
	              }
	            }
	          }
	        }
	      }
	    }

	         if (rsDRUGALLERGY_DRUG_XREF_HIT != null)
	           rsDRUGALLERGY_DRUG_XREF_HIT.close();
	 } finally {
	         close(new PreparedStatement[] { ps0, ps1, ps2, ps3, ps4 });
	 }
	}

	     return (List<DrugAllergyResult>)(List<DrugAllergyResult>)(List<DrugAllergyResult>)(List<DrugAllergyResult>)resultsList;

	}
*/}
