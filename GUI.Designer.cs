
namespace KeyNumberGenerator
{
    partial class GUI
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(GUI));
            this.createdByLabel = new System.Windows.Forms.LinkLabel();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.advancedToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.editPropertiesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.changePropertiesLocationToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.reloadToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.yearLabel = new System.Windows.Forms.Label();
            this.yearInput = new System.Windows.Forms.TextBox();
            this.typeLabel = new System.Windows.Forms.Label();
            this.keyNumberLabel = new System.Windows.Forms.Label();
            this.keyNumberOutput = new System.Windows.Forms.TextBox();
            this.generateButton = new System.Windows.Forms.Button();
            this.numberLabel = new System.Windows.Forms.Label();
            this.numberOutput = new System.Windows.Forms.TextBox();
            this.copyButton = new System.Windows.Forms.Button();
            this.marketLabel = new System.Windows.Forms.Label();
            this.marketInput = new System.Windows.Forms.ComboBox();
            this.durationLabel = new System.Windows.Forms.Label();
            this.durationInput = new System.Windows.Forms.ComboBox();
            this.typeInput = new System.Windows.Forms.ComboBox();
            this.marketPropertiesButton = new System.Windows.Forms.Button();
            this.marketNullButton = new System.Windows.Forms.Button();
            this.durationNullButton = new System.Windows.Forms.Button();
            this.typeNullButton = new System.Windows.Forms.Button();
            this.writerInput = new System.Windows.Forms.TextBox();
            this.writerLabel = new System.Windows.Forms.Label();
            this.clientInput = new System.Windows.Forms.TextBox();
            this.clientLabel = new System.Windows.Forms.Label();
            this.toolTip1 = new System.Windows.Forms.ToolTip(this.components);
            this.durationPropertiesButton = new System.Windows.Forms.Button();
            this.typePropertiesButton = new System.Windows.Forms.Button();
            this.yearPropertiesButton = new System.Windows.Forms.Button();
            this.writerPropertiesButton = new System.Windows.Forms.Button();
            this.clientPropertiesButton = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.toolTip2 = new System.Windows.Forms.ToolTip(this.components);
            this.maskedTextBox1 = new System.Windows.Forms.MaskedTextBox();
            this.maskedTextBox2 = new System.Windows.Forms.MaskedTextBox();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // createdByLabel
            // 
            this.createdByLabel.AutoSize = true;
            this.createdByLabel.LinkColor = System.Drawing.Color.FromArgb(((int)(((byte)(64)))), ((int)(((byte)(64)))), ((int)(((byte)(64)))));
            this.createdByLabel.Location = new System.Drawing.Point(662, 249);
            this.createdByLabel.Name = "createdByLabel";
            this.createdByLabel.Size = new System.Drawing.Size(126, 13);
            this.createdByLabel.TabIndex = 0;
            this.createdByLabel.TabStop = true;
            this.createdByLabel.Text = "Created by Ziggy Badans";
            this.createdByLabel.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkLabel1_LinkClicked);
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.advancedToolStripMenuItem,
            this.reloadToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(800, 24);
            this.menuStrip1.TabIndex = 1;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // advancedToolStripMenuItem
            // 
            this.advancedToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.editPropertiesToolStripMenuItem,
            this.changePropertiesLocationToolStripMenuItem});
            this.advancedToolStripMenuItem.Name = "advancedToolStripMenuItem";
            this.advancedToolStripMenuItem.Size = new System.Drawing.Size(72, 20);
            this.advancedToolStripMenuItem.Text = "Advanced";
            // 
            // editPropertiesToolStripMenuItem
            // 
            this.editPropertiesToolStripMenuItem.Name = "editPropertiesToolStripMenuItem";
            this.editPropertiesToolStripMenuItem.Size = new System.Drawing.Size(226, 22);
            this.editPropertiesToolStripMenuItem.Text = "Edit properties...";
            this.editPropertiesToolStripMenuItem.ToolTipText = "Opens the properties file in the default text editor";
            // 
            // changePropertiesLocationToolStripMenuItem
            // 
            this.changePropertiesLocationToolStripMenuItem.Name = "changePropertiesLocationToolStripMenuItem";
            this.changePropertiesLocationToolStripMenuItem.Size = new System.Drawing.Size(226, 22);
            this.changePropertiesLocationToolStripMenuItem.Text = "Change properties location...";
            this.changePropertiesLocationToolStripMenuItem.ToolTipText = "Set a custom directory for the properties file";
            // 
            // reloadToolStripMenuItem
            // 
            this.reloadToolStripMenuItem.Name = "reloadToolStripMenuItem";
            this.reloadToolStripMenuItem.Size = new System.Drawing.Size(55, 20);
            this.reloadToolStripMenuItem.Text = "Reload";
            this.reloadToolStripMenuItem.ToolTipText = "Replaces all text fields currently edited with default values";
            this.reloadToolStripMenuItem.Click += new System.EventHandler(this.reloadToolStripMenuItem_Click);
            // 
            // yearLabel
            // 
            this.yearLabel.AutoSize = true;
            this.yearLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.yearLabel.Location = new System.Drawing.Point(94, 30);
            this.yearLabel.Name = "yearLabel";
            this.yearLabel.Size = new System.Drawing.Size(33, 13);
            this.yearLabel.TabIndex = 4;
            this.yearLabel.Text = "Year";
            // 
            // yearInput
            // 
            this.yearInput.Location = new System.Drawing.Point(97, 46);
            this.yearInput.MaxLength = 4;
            this.yearInput.Name = "yearInput";
            this.yearInput.Size = new System.Drawing.Size(100, 20);
            this.yearInput.TabIndex = 5;
            this.yearInput.TextChanged += new System.EventHandler(this.yearInput_TextChanged);
            this.yearInput.Enter += new System.EventHandler(this.yearInput_Enter);
            this.yearInput.Leave += new System.EventHandler(this.yearInput_Leave);
            // 
            // typeLabel
            // 
            this.typeLabel.AutoSize = true;
            this.typeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.typeLabel.Location = new System.Drawing.Point(9, 192);
            this.typeLabel.Name = "typeLabel";
            this.typeLabel.Size = new System.Drawing.Size(35, 13);
            this.typeLabel.TabIndex = 8;
            this.typeLabel.Text = "Type";
            this.toolTip1.SetToolTip(this.typeLabel, "Make type null");
            // 
            // keyNumberLabel
            // 
            this.keyNumberLabel.AutoSize = true;
            this.keyNumberLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.keyNumberLabel.Location = new System.Drawing.Point(305, 112);
            this.keyNumberLabel.Name = "keyNumberLabel";
            this.keyNumberLabel.Size = new System.Drawing.Size(75, 13);
            this.keyNumberLabel.TabIndex = 10;
            this.keyNumberLabel.Text = "Key Number";
            // 
            // keyNumberOutput
            // 
            this.keyNumberOutput.CharacterCasing = System.Windows.Forms.CharacterCasing.Upper;
            this.keyNumberOutput.Location = new System.Drawing.Point(308, 129);
            this.keyNumberOutput.MaxLength = 30;
            this.keyNumberOutput.Name = "keyNumberOutput";
            this.keyNumberOutput.Size = new System.Drawing.Size(157, 20);
            this.keyNumberOutput.TabIndex = 11;
            this.keyNumberOutput.TextChanged += new System.EventHandler(this.keyNumberOutput_TextChanged);
            this.keyNumberOutput.Leave += new System.EventHandler(this.keyNumberOutput_Leave);
            // 
            // generateButton
            // 
            this.generateButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.generateButton.Location = new System.Drawing.Point(354, 155);
            this.generateButton.Name = "generateButton";
            this.generateButton.Size = new System.Drawing.Size(75, 23);
            this.generateButton.TabIndex = 12;
            this.generateButton.Text = "Generate";
            this.generateButton.UseVisualStyleBackColor = true;
            this.generateButton.Click += new System.EventHandler(this.generateButton_Click);
            // 
            // numberLabel
            // 
            this.numberLabel.AutoSize = true;
            this.numberLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.numberLabel.Location = new System.Drawing.Point(543, 112);
            this.numberLabel.Name = "numberLabel";
            this.numberLabel.Size = new System.Drawing.Size(114, 13);
            this.numberLabel.TabIndex = 13;
            this.numberLabel.Text = "Sequential Number";
            // 
            // numberOutput
            // 
            this.numberOutput.Enabled = false;
            this.numberOutput.Location = new System.Drawing.Point(546, 128);
            this.numberOutput.Name = "numberOutput";
            this.numberOutput.Size = new System.Drawing.Size(47, 20);
            this.numberOutput.TabIndex = 14;
            this.numberOutput.Text = "1";
            this.toolTip1.SetToolTip(this.numberOutput, "This value increases by 1 every time a key is generated");
            // 
            // copyButton
            // 
            this.copyButton.Image = ((System.Drawing.Image)(resources.GetObject("copyButton.Image")));
            this.copyButton.Location = new System.Drawing.Point(471, 126);
            this.copyButton.Name = "copyButton";
            this.copyButton.Size = new System.Drawing.Size(27, 27);
            this.copyButton.TabIndex = 15;
            this.copyButton.UseVisualStyleBackColor = true;
            this.copyButton.Click += new System.EventHandler(this.copyButton_Click);
            // 
            // marketLabel
            // 
            this.marketLabel.AutoSize = true;
            this.marketLabel.BackColor = System.Drawing.SystemColors.Control;
            this.marketLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.marketLabel.Location = new System.Drawing.Point(9, 29);
            this.marketLabel.Name = "marketLabel";
            this.marketLabel.Size = new System.Drawing.Size(46, 13);
            this.marketLabel.TabIndex = 2;
            this.marketLabel.Text = "Market";
            // 
            // marketInput
            // 
            this.marketInput.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.marketInput.FormattingEnabled = true;
            this.marketInput.Items.AddRange(new object[] {
            "AUG",
            "BAL",
            "BEG",
            "BEN",
            "BER",
            "BOW",
            "BUN",
            "BUR",
            "CAI",
            "DAR",
            "DEV",
            "GEE",
            "GLA",
            "GOL",
            "HOB",
            "IPS",
            "LAU",
            "LIN",
            "MAC",
            "MAR",
            "MIL",
            "MUR",
            "MUS",
            "NOW",
            "PIR",
            "QUE",
            "ROC",
            "TOW",
            "WOL",
            "TRSN",
            "AGEN"});
            this.marketInput.Location = new System.Drawing.Point(12, 45);
            this.marketInput.MaxDropDownItems = 60;
            this.marketInput.Name = "marketInput";
            this.marketInput.Size = new System.Drawing.Size(60, 21);
            this.marketInput.TabIndex = 3;
            this.marketInput.Enter += new System.EventHandler(this.marketInput_Enter);
            this.marketInput.Leave += new System.EventHandler(this.marketInput_Leave);
            // 
            // durationLabel
            // 
            this.durationLabel.AutoSize = true;
            this.durationLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.durationLabel.Location = new System.Drawing.Point(9, 111);
            this.durationLabel.Name = "durationLabel";
            this.durationLabel.Size = new System.Drawing.Size(55, 13);
            this.durationLabel.TabIndex = 6;
            this.durationLabel.Text = "Duration";
            // 
            // durationInput
            // 
            this.durationInput.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.durationInput.FormattingEnabled = true;
            this.durationInput.Items.AddRange(new object[] {
            "10",
            "15",
            "30",
            "45",
            "60",
            "90"});
            this.durationInput.Location = new System.Drawing.Point(12, 128);
            this.durationInput.Name = "durationInput";
            this.durationInput.Size = new System.Drawing.Size(60, 21);
            this.durationInput.TabIndex = 7;
            this.durationInput.Enter += new System.EventHandler(this.durationInput_Enter);
            this.durationInput.Leave += new System.EventHandler(this.durationInput_Leave);
            // 
            // typeInput
            // 
            this.typeInput.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.typeInput.FormattingEnabled = true;
            this.typeInput.Items.AddRange(new object[] {
            "R",
            "L",
            "SL",
            "X",
            "SX"});
            this.typeInput.Location = new System.Drawing.Point(12, 208);
            this.typeInput.Name = "typeInput";
            this.typeInput.Size = new System.Drawing.Size(60, 21);
            this.typeInput.TabIndex = 9;
            this.typeInput.Enter += new System.EventHandler(this.typeInput_Enter);
            this.typeInput.Leave += new System.EventHandler(this.typeInput_Leave);
            // 
            // marketPropertiesButton
            // 
            this.marketPropertiesButton.Image = ((System.Drawing.Image)(resources.GetObject("marketPropertiesButton.Image")));
            this.marketPropertiesButton.Location = new System.Drawing.Point(12, 72);
            this.marketPropertiesButton.Name = "marketPropertiesButton";
            this.marketPropertiesButton.Size = new System.Drawing.Size(27, 27);
            this.marketPropertiesButton.TabIndex = 16;
            this.toolTip1.SetToolTip(this.marketPropertiesButton, "Save current value as property");
            this.marketPropertiesButton.UseVisualStyleBackColor = true;
            this.marketPropertiesButton.Click += new System.EventHandler(this.marketPropertiesButton_Click);
            // 
            // marketNullButton
            // 
            this.marketNullButton.Image = ((System.Drawing.Image)(resources.GetObject("marketNullButton.Image")));
            this.marketNullButton.Location = new System.Drawing.Point(45, 72);
            this.marketNullButton.Name = "marketNullButton";
            this.marketNullButton.Size = new System.Drawing.Size(27, 27);
            this.marketNullButton.TabIndex = 17;
            this.toolTip1.SetToolTip(this.marketNullButton, "Make market null");
            this.marketNullButton.UseVisualStyleBackColor = true;
            this.marketNullButton.Click += new System.EventHandler(this.marketNullButton_Click);
            // 
            // durationNullButton
            // 
            this.durationNullButton.Image = ((System.Drawing.Image)(resources.GetObject("durationNullButton.Image")));
            this.durationNullButton.Location = new System.Drawing.Point(45, 152);
            this.durationNullButton.Name = "durationNullButton";
            this.durationNullButton.Size = new System.Drawing.Size(27, 27);
            this.durationNullButton.TabIndex = 19;
            this.toolTip1.SetToolTip(this.durationNullButton, "Make duration null");
            this.durationNullButton.UseVisualStyleBackColor = true;
            this.durationNullButton.Click += new System.EventHandler(this.durationNullButton_Click);
            // 
            // typeNullButton
            // 
            this.typeNullButton.Image = ((System.Drawing.Image)(resources.GetObject("typeNullButton.Image")));
            this.typeNullButton.Location = new System.Drawing.Point(45, 235);
            this.typeNullButton.Name = "typeNullButton";
            this.typeNullButton.Size = new System.Drawing.Size(27, 27);
            this.typeNullButton.TabIndex = 21;
            this.toolTip1.SetToolTip(this.typeNullButton, "Make type null");
            this.typeNullButton.UseVisualStyleBackColor = true;
            this.typeNullButton.Click += new System.EventHandler(this.typeNullButton_Click);
            // 
            // writerInput
            // 
            this.writerInput.Location = new System.Drawing.Point(97, 129);
            this.writerInput.Name = "writerInput";
            this.writerInput.Size = new System.Drawing.Size(100, 20);
            this.writerInput.TabIndex = 23;
            this.writerInput.Enter += new System.EventHandler(this.writerInput_Enter);
            this.writerInput.Leave += new System.EventHandler(this.writerInput_Leave);
            // 
            // writerLabel
            // 
            this.writerLabel.AutoSize = true;
            this.writerLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.writerLabel.Location = new System.Drawing.Point(94, 113);
            this.writerLabel.Name = "writerLabel";
            this.writerLabel.Size = new System.Drawing.Size(41, 13);
            this.writerLabel.TabIndex = 22;
            this.writerLabel.Text = "Writer";
            // 
            // clientInput
            // 
            this.clientInput.Location = new System.Drawing.Point(97, 209);
            this.clientInput.Name = "clientInput";
            this.clientInput.Size = new System.Drawing.Size(100, 20);
            this.clientInput.TabIndex = 25;
            this.clientInput.Enter += new System.EventHandler(this.clientInput_Enter);
            this.clientInput.Leave += new System.EventHandler(this.clientInput_Leave);
            // 
            // clientLabel
            // 
            this.clientLabel.AutoSize = true;
            this.clientLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.clientLabel.Location = new System.Drawing.Point(94, 193);
            this.clientLabel.Name = "clientLabel";
            this.clientLabel.Size = new System.Drawing.Size(39, 13);
            this.clientLabel.TabIndex = 24;
            this.clientLabel.Text = "Client";
            // 
            // durationPropertiesButton
            // 
            this.durationPropertiesButton.Image = ((System.Drawing.Image)(resources.GetObject("durationPropertiesButton.Image")));
            this.durationPropertiesButton.Location = new System.Drawing.Point(12, 152);
            this.durationPropertiesButton.Name = "durationPropertiesButton";
            this.durationPropertiesButton.Size = new System.Drawing.Size(27, 27);
            this.durationPropertiesButton.TabIndex = 26;
            this.toolTip1.SetToolTip(this.durationPropertiesButton, "Save current value as property");
            this.durationPropertiesButton.UseVisualStyleBackColor = true;
            this.durationPropertiesButton.Click += new System.EventHandler(this.durationPropertiesButton_Click);
            // 
            // typePropertiesButton
            // 
            this.typePropertiesButton.Image = ((System.Drawing.Image)(resources.GetObject("typePropertiesButton.Image")));
            this.typePropertiesButton.Location = new System.Drawing.Point(12, 235);
            this.typePropertiesButton.Name = "typePropertiesButton";
            this.typePropertiesButton.Size = new System.Drawing.Size(27, 27);
            this.typePropertiesButton.TabIndex = 27;
            this.toolTip1.SetToolTip(this.typePropertiesButton, "Save current value as property");
            this.typePropertiesButton.UseVisualStyleBackColor = true;
            this.typePropertiesButton.Click += new System.EventHandler(this.typePropertiesButton_Click);
            // 
            // yearPropertiesButton
            // 
            this.yearPropertiesButton.Image = ((System.Drawing.Image)(resources.GetObject("yearPropertiesButton.Image")));
            this.yearPropertiesButton.Location = new System.Drawing.Point(97, 72);
            this.yearPropertiesButton.Name = "yearPropertiesButton";
            this.yearPropertiesButton.Size = new System.Drawing.Size(27, 27);
            this.yearPropertiesButton.TabIndex = 28;
            this.toolTip1.SetToolTip(this.yearPropertiesButton, "Save current value as property");
            this.yearPropertiesButton.UseVisualStyleBackColor = true;
            this.yearPropertiesButton.Click += new System.EventHandler(this.yearPropertiesButton_Click);
            // 
            // writerPropertiesButton
            // 
            this.writerPropertiesButton.Image = ((System.Drawing.Image)(resources.GetObject("writerPropertiesButton.Image")));
            this.writerPropertiesButton.Location = new System.Drawing.Point(97, 155);
            this.writerPropertiesButton.Name = "writerPropertiesButton";
            this.writerPropertiesButton.Size = new System.Drawing.Size(27, 27);
            this.writerPropertiesButton.TabIndex = 29;
            this.toolTip1.SetToolTip(this.writerPropertiesButton, "Save current value as property");
            this.writerPropertiesButton.UseVisualStyleBackColor = true;
            this.writerPropertiesButton.Click += new System.EventHandler(this.writerPropertiesButton_Click);
            // 
            // clientPropertiesButton
            // 
            this.clientPropertiesButton.Image = ((System.Drawing.Image)(resources.GetObject("clientPropertiesButton.Image")));
            this.clientPropertiesButton.Location = new System.Drawing.Point(97, 235);
            this.clientPropertiesButton.Name = "clientPropertiesButton";
            this.clientPropertiesButton.Size = new System.Drawing.Size(27, 27);
            this.clientPropertiesButton.TabIndex = 30;
            this.toolTip1.SetToolTip(this.clientPropertiesButton, "Save current value as property");
            this.clientPropertiesButton.UseVisualStyleBackColor = true;
            this.clientPropertiesButton.Click += new System.EventHandler(this.clientPropertiesButton_Click);
            // 
            // button1
            // 
            this.button1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.button1.FlatAppearance.BorderColor = System.Drawing.SystemColors.Control;
            this.button1.FlatAppearance.BorderSize = 0;
            this.button1.FlatAppearance.MouseDownBackColor = System.Drawing.SystemColors.Control;
            this.button1.FlatAppearance.MouseOverBackColor = System.Drawing.SystemColors.Control;
            this.button1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.button1.Location = new System.Drawing.Point(0, 0);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(800, 275);
            this.button1.TabIndex = 31;
            this.button1.TabStop = false;
            this.button1.Text = "button1";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // maskedTextBox1
            // 
            this.maskedTextBox1.Location = new System.Drawing.Point(237, 46);
            this.maskedTextBox1.Mask = "0099";
            this.maskedTextBox1.Name = "maskedTextBox1";
            this.maskedTextBox1.Size = new System.Drawing.Size(100, 20);
            this.maskedTextBox1.TabIndex = 32;
            // 
            // maskedTextBox2
            // 
            this.maskedTextBox2.Location = new System.Drawing.Point(436, 193);
            this.maskedTextBox2.Mask = ">LLL?0099L???-LL??-0099L???-00";
            this.maskedTextBox2.Name = "maskedTextBox2";
            this.maskedTextBox2.Size = new System.Drawing.Size(221, 20);
            this.maskedTextBox2.TabIndex = 33;
            // 
            // GUI
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 275);
            this.Controls.Add(this.maskedTextBox2);
            this.Controls.Add(this.maskedTextBox1);
            this.Controls.Add(this.clientPropertiesButton);
            this.Controls.Add(this.writerPropertiesButton);
            this.Controls.Add(this.yearPropertiesButton);
            this.Controls.Add(this.typePropertiesButton);
            this.Controls.Add(this.durationPropertiesButton);
            this.Controls.Add(this.clientInput);
            this.Controls.Add(this.clientLabel);
            this.Controls.Add(this.writerInput);
            this.Controls.Add(this.writerLabel);
            this.Controls.Add(this.typeNullButton);
            this.Controls.Add(this.durationNullButton);
            this.Controls.Add(this.marketNullButton);
            this.Controls.Add(this.marketPropertiesButton);
            this.Controls.Add(this.copyButton);
            this.Controls.Add(this.numberOutput);
            this.Controls.Add(this.numberLabel);
            this.Controls.Add(this.generateButton);
            this.Controls.Add(this.keyNumberOutput);
            this.Controls.Add(this.keyNumberLabel);
            this.Controls.Add(this.typeInput);
            this.Controls.Add(this.typeLabel);
            this.Controls.Add(this.durationInput);
            this.Controls.Add(this.durationLabel);
            this.Controls.Add(this.yearInput);
            this.Controls.Add(this.yearLabel);
            this.Controls.Add(this.marketInput);
            this.Controls.Add(this.marketLabel);
            this.Controls.Add(this.createdByLabel);
            this.Controls.Add(this.menuStrip1);
            this.Controls.Add(this.button1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "GUI";
            this.Text = "Key Number Generator";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.LinkLabel createdByLabel;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem advancedToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem editPropertiesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem changePropertiesLocationToolStripMenuItem;
        private System.Windows.Forms.Label yearLabel;
        private System.Windows.Forms.TextBox yearInput;
        private System.Windows.Forms.Label typeLabel;
        private System.Windows.Forms.Label keyNumberLabel;
        private System.Windows.Forms.TextBox keyNumberOutput;
        private System.Windows.Forms.Button generateButton;
        private System.Windows.Forms.Label numberLabel;
        private System.Windows.Forms.TextBox numberOutput;
        private System.Windows.Forms.Button copyButton;
        private System.Windows.Forms.Label marketLabel;
        private System.Windows.Forms.ComboBox marketInput;
        private System.Windows.Forms.Label durationLabel;
        private System.Windows.Forms.ComboBox durationInput;
        private System.Windows.Forms.ComboBox typeInput;
        private System.Windows.Forms.Button marketPropertiesButton;
        private System.Windows.Forms.Button marketNullButton;
        private System.Windows.Forms.Button durationNullButton;
        private System.Windows.Forms.Button typeNullButton;
        private System.Windows.Forms.ToolStripMenuItem reloadToolStripMenuItem;
        private System.Windows.Forms.TextBox writerInput;
        private System.Windows.Forms.Label writerLabel;
        private System.Windows.Forms.TextBox clientInput;
        private System.Windows.Forms.Label clientLabel;
        private System.Windows.Forms.ToolTip toolTip1;
        private System.Windows.Forms.Button durationPropertiesButton;
        private System.Windows.Forms.Button typePropertiesButton;
        private System.Windows.Forms.Button yearPropertiesButton;
        private System.Windows.Forms.Button writerPropertiesButton;
        private System.Windows.Forms.Button clientPropertiesButton;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ToolTip toolTip2;
        private System.Windows.Forms.MaskedTextBox maskedTextBox1;
        private System.Windows.Forms.MaskedTextBox maskedTextBox2;
    }
}

