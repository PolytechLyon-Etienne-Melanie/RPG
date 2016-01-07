package test.rpg.editor.dialog;

import java.awt.Frame;

import test.rpg.editor.dialog.property.JEventPropertyCombo;
import test.rpg.editor.dialog.property.JEventPropertyInt;
import test.rpg.editor.dialog.property.JEventPropertyString;
import test.rpg.engine.story.event.EventEntity;

public class EntityPropertyDialog extends PropertyDialog<EventEntity>
{
	private JEventPropertyString name;
	private JEventPropertyInt lvl;
	private JEventPropertyCombo<EventEntity.ClasseE> classe;
	
	public EntityPropertyDialog(Frame parent)
	{
		super(parent, new EventEntity(), "Monstre", true);
	}

	public EntityPropertyDialog(Frame frame, EventEntity e)
	{
		super(frame, e, "Monstre", false);
	}

	@Override
	protected void addProperties(Frame frame)
	{
		name = new JEventPropertyString(frame, value.getNom());
		this.addProperty("Nom", name);
		lvl = new JEventPropertyInt(frame, value.getNiveau());
		this.addProperty("Niveau", lvl);
		classe = new JEventPropertyCombo<EventEntity.ClasseE>(frame, EventEntity.ClasseE.class, value.getClasseE());
		this.addProperty("Classe", classe);
	}

	@Override
	protected EventEntity setValues()
	{
		return new EventEntity(name.getValue(), lvl.getValue(), classe.getValue());
	}
}