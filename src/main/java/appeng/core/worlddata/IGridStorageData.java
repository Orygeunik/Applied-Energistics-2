/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2015, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.core.worlddata;

import javax.annotation.Nonnull;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;

import appeng.me.GridStorage;

/**
 * @author thatsIch
 * @version rv3 - 30.05.2015
 * @since rv3 30.05.2015
 */
public interface IGridStorageData {

    static IGridStorageData get(MinecraftServer server) {
        var overworld = server.getLevel(ServerLevel.OVERWORLD);
        if (overworld == null) {
            throw new IllegalStateException("Cannot retrieve grid storage data for a server that has no overworld.");
        }
        return overworld.getDataStorage().computeIfAbsent(
                GridStorageData::load,
                GridStorageData::new,
                GridStorageData.NAME);
    }

    GridStorage getGridStorage(long storageID);

    @Nonnull
    GridStorage getNewGridStorage();

    void destroyGridStorage(long id);

    int getNextOrderedValue(String name, int firstValue);
}
