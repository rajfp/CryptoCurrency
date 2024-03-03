package com.plcoding.cryptocurrencyappyt.data.remote

import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.domain.model.Coin

class CoinDataTransformer {

    fun transformData(coinDto: CoinDto): Coin {
        return Coin(
            id = coinDto.id,
            isActive = coinDto.isActive,
            name = coinDto.name,
            rank = coinDto.rank,
            symbol = coinDto.symbol
        )
    }

}