package com.example.budgetapp.automation

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AutomationTypeConverters {

    @TypeConverter
    fun fromRuleConditionList(value: List<RuleCondition>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toRuleConditionList(value: String): List<RuleCondition> {
        val listType = object : TypeToken<List<RuleCondition>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromAutomationActionList(value: List<AutomationAction>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toAutomationActionList(value: String): List<AutomationAction> {
        val listType = object : TypeToken<List<AutomationAction>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromTransferConditionList(value: List<TransferCondition>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toTransferConditionList(value: String): List<TransferCondition> {
        val listType = object : TypeToken<List<TransferCondition>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun fromConditionType(value: ConditionType): String {
        return value.name
    }

    @TypeConverter
    fun toConditionType(value: String): ConditionType {
        return ConditionType.valueOf(value)
    }

    @TypeConverter
    fun fromConditionOperator(value: ConditionOperator): String {
        return value.name
    }

    @TypeConverter
    fun toConditionOperator(value: String): ConditionOperator {
        return ConditionOperator.valueOf(value)
    }

    @TypeConverter
    fun fromActionType(value: ActionType): String {
        return value.name
    }

    @TypeConverter
    fun toActionType(value: String): ActionType {
        return ActionType.valueOf(value)
    }

    @TypeConverter
    fun fromAutomationCreatedBy(value: AutomationCreatedBy): String {
        return value.name
    }

    @TypeConverter
    fun toAutomationCreatedBy(value: String): AutomationCreatedBy {
        return AutomationCreatedBy.valueOf(value)
    }

    @TypeConverter
    fun fromTransferType(value: TransferType): String {
        return value.name
    }

    @TypeConverter
    fun toTransferType(value: String): TransferType {
        return TransferType.valueOf(value)
    }

    @TypeConverter
    fun fromTransferFrequency(value: TransferFrequency): String {
        return value.name
    }

    @TypeConverter
    fun toTransferFrequency(value: String): TransferFrequency {
        return TransferFrequency.valueOf(value)
    }

    @TypeConverter
    fun fromTransferConditionType(value: TransferConditionType): String {
        return value.name
    }

    @TypeConverter
    fun toTransferConditionType(value: String): TransferConditionType {
        return TransferConditionType.valueOf(value)
    }

    @TypeConverter
    fun fromStringStringMap(value: Map<String, String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringStringMap(value: String): Map<String, String> {
        val mapType = object : TypeToken<Map<String, String>>() {}.type
        return Gson().fromJson(value, mapType) ?: emptyMap()
    }
}