/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package net.royaltechnica.launcher.creator.dialog;

import net.royaltechnica.launcher.creator.model.creator.Problem;
import net.royaltechnica.launcher.creator.model.swing.ProblemTableModel;
import net.royaltechnica.launcher.swing.SwingHelper;
import net.royaltechnica.launcher.swing.TextFieldPopupMenu;
import net.miginfonet.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class ProblemViewer extends JDialog {

    private static final String DEFAULT_EXPLANATION = "Select a problem on the left to see the explanation here.";
    private final ProblemTable problemTable = new ProblemTable();
    private final ProblemTableModel problemTableModel;
    private final JTextArea explanationText = new JTextArea(DEFAULT_EXPLANATION);

    public ProblemViewer(Window parent, List<Problem> problems) {
        super(parent, "Potential Problems", ModalityType.DOCUMENT_MODAL);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(parent);

        problemTableModel = new ProblemTableModel(problems);
        problemTable.setModel(problemTableModel);
    }

    private void initComponents() {
        explanationText.setComponentPopupMenu(TextFieldPopupMenu.INSTANCE);

        explanationText.setFont(new JTextField().getFont());
        explanationText.setEditable(false);
        explanationText.setLineWrap(true);
        explanationText.setWrapStyleWord(true);

        JPanel container = new JPanel();
        container.setLayout(new MigLayout("fill, insets dialog"));

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                SwingHelper.wrapScrollPane(problemTable), SwingHelper.
                wrapScrollPane(explanationText));
        splitPane.setDividerLocation(180);
        SwingHelper.flattenJSplitPane(splitPane);

        container.add(splitPane, "grow, w 220:500, h 240, wrap");

        JButton closeButton = new JButton("Close");
        container.add(closeButton, "tag cancel, gaptop unrel");

        add(container, BorderLayout.CENTER);

        getRootPane().setDefaultButton(closeButton);
        getRootPane().registerKeyboardAction(e -> closeButton.doClick(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        problemTable.getSelectionModel().addListSelectionListener(e -> {
            Problem selected = problemTableModel.getProblem(problemTable.getSelectedRow());
            if (selected != null) {
                SwingHelper.setTextAndResetCaret(explanationText, selected.getExplanation());
            }
        });

        closeButton.addActionListener(e -> dispose());
    }
}
