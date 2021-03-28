package com.selen.myweather.database.mapper

import com.selen.myweather.database.entity.CityEntity
import com.selen.myweather.model.CityDatabaseModel

/**
 * @author Pyaterko Aleksey
 *
 * маппер для перевода entity в model и наоборот
 */
class CityMapper {

    fun entityToModel(entity: CityEntity?): CityDatabaseModel? {
        if (entity == null) return null

        val model = CityDatabaseModel()
        model.id = entity.id
        model.cityName = entity.cityName ?: ""
        return model
    }

    fun modelToEntity(model: CityDatabaseModel?): CityEntity? {
        if (model == null) return null

        val entity = CityEntity()
        entity.id = model.id
        entity.cityName = model.cityName
        return entity
    }

    fun entityListToModelList(entityList: List<CityEntity?>?): List<CityDatabaseModel> {
        if (entityList == null) return listOf()

        val modelList: MutableList<CityDatabaseModel> = mutableListOf()
        entityList.forEach { entity ->
            entity?.let {
                val model = CityDatabaseModel()
                model.id = it.id
                model.cityName = it.cityName ?: ""
                modelList.add(model)
            } ?: modelList.add(CityDatabaseModel())
        }
        return modelList
    }

    fun modelListToEntityList(modelList: List<CityDatabaseModel?>?): List<CityEntity?> {
        if (modelList == null) return listOf()

        val entityList: MutableList<CityEntity?> = mutableListOf()
        modelList.forEach { model ->
            model?.let {
                val entity = CityEntity()
                entity.id = it.id
                entity.cityName = it.cityName
                entityList.add(entity)
            } ?: entityList.add(null)
        }
        return entityList
    }

}