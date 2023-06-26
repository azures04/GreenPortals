package fr.azures.mod.greenportals.ui;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.azures.mod.greenportals.utils.Temp;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class PortalGunConfigScreenUI extends Screen {
    private TextFieldWidget xCoord;
    private TextFieldWidget yCoord;
    private TextFieldWidget zCoord;
    int currentDimensionIndex = 0;
    String currentDimensionId = "minecraft:overworld";
    public PortalGunConfigScreenUI() {
        super(new StringTextComponent("Portal Gun Config"));
    }

    @Override
    protected void init() {
    	List<RegistryKey<World>> dimensions = new ArrayList<>(ServerLifecycleHooks.getCurrentServer().levelKeys());
        xCoord = new TextFieldWidget(font, width / 2 - 80, height / 2 - 50, 160, 20, new StringTextComponent("X Coord"));
        yCoord = new TextFieldWidget(font, width / 2 - 80, height / 2 - 20, 160, 20, new StringTextComponent("Y Coord"));
        zCoord = new TextFieldWidget(font, width / 2 - 80, height / 2 + 10, 160, 20, new StringTextComponent("Z Coord"));

        xCoord.setMessage(new StringTextComponent("X Coord"));
        yCoord.setMessage(new StringTextComponent("Y Coord"));
        zCoord.setMessage(new StringTextComponent("Z Coord"));
        
        addButton(new Button(width / 2 - 50, height / 2 + 50, 100, 20, new StringTextComponent("minecraft:overworld"), (button) -> {
        	currentDimensionIndex++;
        	if (currentDimensionIndex >= dimensions.size()) {
                currentDimensionIndex = 0;
            }
            RegistryKey<World> dimension = dimensions.get(currentDimensionIndex);
            currentDimensionId = dimension.location().toString();
            button.setMessage(new StringTextComponent(dimension.location().toString()));
        }));

        addButton(new Button(width / 2 - 50, height / 2 + 80, 100, 20, new StringTextComponent("Valider"), (button) -> {
        	if (xCoord.getValue() != null && yCoord.getValue() != null && zCoord.getValue() != null ) {
        		try {
            		Temp.xCoord = Integer.valueOf(xCoord.getValue().toString());
            		Temp.yCoord = Integer.valueOf(yCoord.getValue().toString());
            		Temp.zCoord = Integer.valueOf(zCoord.getValue().toString());
            		Temp.dimCoord = currentDimensionId;
            		System.out.println(Temp.xCoord);
            		System.out.println(Temp.yCoord);
            		System.out.println(Temp.zCoord);
            		System.out.println(Temp.dimCoord);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        }));
        children.add(xCoord);
        children.add(yCoord);
        children.add(zCoord);
    }

    @Override
    public void tick() {
        xCoord.tick();
        yCoord.tick();
        zCoord.tick();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        xCoord.render(matrices, mouseX, mouseY, delta);
        yCoord.render(matrices, mouseX, mouseY, delta);
        zCoord.render(matrices, mouseX, mouseY, delta);
    }
    
    @Override
    public boolean isPauseScreen() {
    	return false;
    }
}