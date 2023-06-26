package fr.azures.mod.greenportals.ui;

import com.github.wintersteve25.tau.Tau;
import com.github.wintersteve25.tau.components.Center;
import com.github.wintersteve25.tau.components.Sized;
import com.github.wintersteve25.tau.components.TextField;
import com.github.wintersteve25.tau.components.base.UIComponent;
import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;
import com.github.wintersteve25.tau.utils.Size;

import net.minecraft.util.text.StringTextComponent;

public class PortalGunConfigScreenUI implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
            new Sized(
                Size.staticSize(200, 20),
                new TextField.Builder()
                    .withHintText(new StringTextComponent("hint text"))
                    .withOnChange(Tau.LOGGER::info)
            )
        );
    }
}