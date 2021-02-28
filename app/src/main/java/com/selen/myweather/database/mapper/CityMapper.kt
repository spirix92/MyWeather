package com.selen.myweather.database.mapper

import com.selen.myweather.database.entity.CityEntity
import com.selen.myweather.database.model.CityModel

/**
 * @author Pyaterko Aleksey
 *
 * маппер для перевода entity в model и наоборот
 */
class CityMapper {

    fun entityToModel(entity: CityEntity?): CityModel? {
        if (entity == null) return null

        val model = CityModel()
        model.id = entity.id
        model.cityName = entity.cityName
        return model
    }

    fun modelToEntity(model: CityModel?): CityEntity? {
        if (model == null) return null

        val entity = CityEntity()
        entity.id = model.id
        entity.cityName = model.cityName
        return entity
    }

    fun entityListToModelList(entityList: List<CityEntity?>?): List<CityModel?>? {
        if (entityList == null) return null

        val modelList: MutableList<CityModel?> = mutableListOf()
        entityList.forEach { entity ->
            entity?.let {
                val model = CityModel()
                model.id = it.id
                model.cityName = it.cityName
                modelList.add(model)
            } ?: modelList.add(null)
        }
        return modelList
    }

    fun modelListToEntityList(modelList: List<CityModel?>?): List<CityEntity?>? {
        if (modelList == null) return null

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