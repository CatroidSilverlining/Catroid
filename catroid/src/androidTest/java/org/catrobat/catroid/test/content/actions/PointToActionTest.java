/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2016 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.test.content.actions;

import android.test.AndroidTestCase;

import com.badlogic.gdx.scenes.scene2d.Action;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.content.ActionFactory;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Scene;
import org.catrobat.catroid.content.SingleSprite;
import org.catrobat.catroid.content.Sprite;

public class PointToActionTest extends AndroidTestCase {

	private final float delta = 1e-7f;

	public void testPointTo() {
		Sprite sprite = new SingleSprite("sprite");
		Sprite pointedSprite = new SingleSprite("pointedSprite");
		Project project = new Project();
		Scene scene = new Scene();
		project.addScene(scene);
		project.getDefaultScene().addSprite(sprite);
		project.getDefaultScene().addSprite(pointedSprite);
		ProjectManager.getInstance().setProject(project);

		ActionFactory factory = sprite.getActionFactory();
		Action pointToAction = factory.createPointToAction(sprite, pointedSprite);

		pointedSprite.look.setPosition(200f, 0f);
		pointToAction.act(1.0f);
		assertEquals("Wrong direction", 90f, sprite.look.getDirectionInUserInterfaceDimensionUnit(), delta);

		pointedSprite.look.setPosition(200f, 200f);
		pointToAction.restart();
		pointToAction.act(1.0f);
		assertEquals("Wrong direction", 45f, sprite.look.getDirectionInUserInterfaceDimensionUnit(), delta);

		pointedSprite.look.setPosition(0f, 200f);
		pointToAction.restart();
		pointToAction.act(1.0f);
		assertEquals("Wrong direction", 0f, sprite.look.getDirectionInUserInterfaceDimensionUnit(), delta);

		pointedSprite.look.setPosition(-200f, 200f);
		pointToAction.restart();
		pointToAction.act(1.0f);
		assertEquals("Wrong direction", -45f, sprite.look.getDirectionInUserInterfaceDimensionUnit(), delta);

		pointedSprite.look.setPosition(-200f, 0f);
		pointToAction.restart();
		pointToAction.act(1.0f);
		assertEquals("Wrong direction", -90f, sprite.look.getDirectionInUserInterfaceDimensionUnit(), delta);

		pointedSprite.look.setPosition(-200f, -200f);
		pointToAction.restart();
		pointToAction.act(1.0f);
		assertEquals("Wrong direction", -135f, sprite.look.getDirectionInUserInterfaceDimensionUnit(), delta);

		pointedSprite.look.setPosition(0f, -200f);
		pointToAction.restart();
		pointToAction.act(1.0f);
		assertEquals("Wrong direction", 180f, sprite.look.getDirectionInUserInterfaceDimensionUnit(), delta);

		pointedSprite.look.setPosition(200f, -200f);
		pointToAction.restart();
		pointToAction.act(1.0f);
		assertEquals("Wrong direction", 135f, sprite.look.getDirectionInUserInterfaceDimensionUnit(), delta);
	}
}
