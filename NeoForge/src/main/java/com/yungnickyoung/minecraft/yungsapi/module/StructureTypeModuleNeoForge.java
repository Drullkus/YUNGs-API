package com.yungnickyoung.minecraft.yungsapi.module;

import com.yungnickyoung.minecraft.yungsapi.YungsApiNeoForge;
import com.yungnickyoung.minecraft.yungsapi.autoregister.AutoRegisterField;
import com.yungnickyoung.minecraft.yungsapi.autoregister.AutoRegistrationManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.RegisterEvent;

/**
 * Registration of structure types.
 */
public class StructureTypeModuleNeoForge {
    public static void processEntries() {
        YungsApiNeoForge.loadingContextEventBus.addListener(StructureTypeModuleNeoForge::registerStructureTypes);
    }

    private static void registerStructureTypes(RegisterEvent event) {
        event.register(Registries.STRUCTURE_TYPE, helper -> AutoRegistrationManager.STRUCTURE_TYPES.stream()
                .filter(data -> !data.processed())
                .forEach(data -> registerStructureType(data, helper)));
    }

    private static void registerStructureType(AutoRegisterField data, RegisterEvent.RegisterHelper<StructureType<?>> helper) {
        StructureType<?> structureType = (StructureType<?>) data.object();
        helper.register(data.name(), structureType);
        data.markProcessed();
    }
}