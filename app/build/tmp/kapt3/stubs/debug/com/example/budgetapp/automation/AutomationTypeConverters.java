package com.example.budgetapp.automation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0016\u0010\u0007\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000fH\u0007J\u0016\u0010\u0010\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00110\bH\u0007J\u0016\u0010\u0012\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0007J\u001c\u0010\u0013\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0014H\u0007J\u0016\u0010\u0015\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00160\bH\u0007J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0018H\u0007J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001aH\u0007J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001cH\u0007J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010 \u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u001c\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00142\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\'\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010(\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a8\u0006)"}, d2 = {"Lcom/example/budgetapp/automation/AutomationTypeConverters;", "", "()V", "fromActionType", "", "value", "Lcom/example/budgetapp/automation/ActionType;", "fromAutomationActionList", "", "Lcom/example/budgetapp/automation/AutomationAction;", "fromAutomationCreatedBy", "Lcom/example/budgetapp/automation/AutomationCreatedBy;", "fromConditionOperator", "Lcom/example/budgetapp/automation/ConditionOperator;", "fromConditionType", "Lcom/example/budgetapp/automation/ConditionType;", "fromRuleConditionList", "Lcom/example/budgetapp/automation/RuleCondition;", "fromStringList", "fromStringStringMap", "", "fromTransferConditionList", "Lcom/example/budgetapp/automation/TransferCondition;", "fromTransferConditionType", "Lcom/example/budgetapp/automation/TransferConditionType;", "fromTransferFrequency", "Lcom/example/budgetapp/automation/TransferFrequency;", "fromTransferType", "Lcom/example/budgetapp/automation/TransferType;", "toActionType", "toAutomationActionList", "toAutomationCreatedBy", "toConditionOperator", "toConditionType", "toRuleConditionList", "toStringList", "toStringStringMap", "toTransferConditionList", "toTransferConditionType", "toTransferFrequency", "toTransferType", "CashInCashOut_debug"})
public final class AutomationTypeConverters {
    
    public AutomationTypeConverters() {
        super();
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromRuleConditionList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.RuleCondition> value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.RuleCondition> toRuleConditionList(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromAutomationActionList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.AutomationAction> value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.AutomationAction> toAutomationActionList(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromTransferConditionList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.TransferCondition> value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.TransferCondition> toTransferConditionList(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromStringList(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> toStringList(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromConditionType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ConditionType value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ConditionType toConditionType(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromConditionOperator(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ConditionOperator value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ConditionOperator toConditionOperator(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromActionType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ActionType value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ActionType toActionType(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromAutomationCreatedBy(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.AutomationCreatedBy value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.AutomationCreatedBy toAutomationCreatedBy(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromTransferType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.TransferType value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.TransferType toTransferType(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromTransferFrequency(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.TransferFrequency value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.TransferFrequency toTransferFrequency(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromTransferConditionType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.TransferConditionType value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.TransferConditionType toTransferConditionType(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromStringStringMap(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> toStringStringMap(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
}