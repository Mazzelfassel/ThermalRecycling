/*
 * This file is part of ThermalRecycling, licensed under the MIT License (MIT).
 *
 * Copyright (c) OreCruncher
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.blockartistry.mod.ThermalRecycling.events;

import org.blockartistry.mod.ThermalRecycling.machines.MachineVending;
import org.blockartistry.mod.ThermalRecycling.machines.entity.VendingTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import com.google.common.base.Predicate;

public class VendingMachineBreakHandler implements Predicate<BreakEvent> {

	@Override
	public boolean apply(final BreakEvent evt) {
		
		if(!evt.isCanceled() && evt.block instanceof MachineVending) {
			
			// Do the check only if player is non-OP
			if(!evt.getPlayer().capabilities.isCreativeMode) {
				final TileEntity te = evt.world.getTileEntity(evt.x, evt.y, evt.z);
				if(te instanceof VendingTileEntity) {
					final VendingTileEntity vending = (VendingTileEntity) te;
					if(!vending.okToBreak(evt.getPlayer()))
						evt.setCanceled(true);
				}
			}
		}
		
		return true;
	}
}
