using Octokit;
using System;
using System.ComponentModel;
using System.Drawing;
using System.Net;
using System.Windows.Forms;

namespace KeyNumberGenerator
{
    public partial class GUI : Form
    {
        readonly KeyNumberGenerator keyNumberGenerator = new KeyNumberGenerator();
        string version;

        public GUI()
        {
            InitializeComponent();

            if (Array.Exists(KeyNumberGenerator.markets, element => element == keyNumberGenerator.Load("market")))
            {
                marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
            } else
            {
                marketInput.SelectedIndex = 0;
            }
            if (Array.Exists(KeyNumberGenerator.durations, element => element == int.Parse(keyNumberGenerator.Load("duration"))))
            {
                durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
            }
            else
            {
                durationInput.SelectedIndex = 0;
            }
            if (Array.Exists(KeyNumberGenerator.types, element => element == keyNumberGenerator.Load("type")))
            {
                typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
            }
            else
            {
                typeInput.SelectedIndex = 0;
            }
            
            yearInput.Text = keyNumberGenerator.Load("year");
            writerInput.Text = keyNumberGenerator.Load("writerInitial");
            clientInput.Text = keyNumberGenerator.Load("clientInitial");

            keyNumberGenerator.SetMarket(marketInput.Text);
            keyNumberGenerator.SetDuration(int.Parse(durationInput.Text));
            keyNumberGenerator.SetType(typeInput.Text);
            keyNumberGenerator.SetYear(yearInput.Text);
            keyNumberGenerator.SetWriterI(writerInput.Text);
            keyNumberGenerator.SetClientI(clientInput.Text);

            if (!keyNumberGenerator.marketReady)
            {
                marketLabel.BackColor = Color.AntiqueWhite;
            }
            if (!keyNumberGenerator.durationReady)
            {
                durationLabel.BackColor = Color.AntiqueWhite;
            }
            if (!keyNumberGenerator.typeReady)
            {
                typeLabel.BackColor = Color.AntiqueWhite;
            }
            if (!keyNumberGenerator.yearReady || yearInput.Text == "Type year")
            {
                yearLabel.BackColor = Color.AntiqueWhite;
            }
            if (writerInput.Text == "Type name")
            {
                writerLabel.BackColor = Color.AntiqueWhite;
            }
            if (clientInput.Text == "Type client name")
            {
                clientLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            //System.Diagnostics.Process.Start("https://github.com/ziggybadans/key-number-generator");
        }

        private void generateButton_Click(object sender, EventArgs e)
        {
            Properties.Settings.Default.firstLaunch = false;
            updateLabel.Visible = false;

            keyNumberGenerator.SetMarket(marketInput.Text);
            keyNumberGenerator.SetYear(yearInput.Text);
            keyNumberGenerator.SetWriterI(writerInput.Text);
            try
            {
                keyNumberGenerator.SetDuration(int.Parse(durationInput.Text));
            }
            catch (Exception) { }

            keyNumberGenerator.SetType(typeInput.Text);
            keyNumberGenerator.SetClientI(clientInput.Text);

            keyNumberOutput.Text = keyNumberGenerator.Generate();

            if (writerInput.Text == "Type name")
            {
                writerLabel.BackColor = Color.AntiqueWhite;
            }
            if (clientInput.Text == "Type client name")
            {
                clientLabel.BackColor = Color.AntiqueWhite;
            }
            if (yearInput.Text == "Type year")
            {
                yearLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void yearInput_Enter(object sender, EventArgs e)
        {
            yearLabel.BackColor = Color.WhiteSmoke;
            if (yearInput.Text == "Type year")
            {
                yearInput.Text = "";
            }
        }

        private void yearInput_Leave(object sender, EventArgs e)
        {
            if (yearInput.Text == "")
            {
                yearInput.Text = "Type year";
            }
            keyNumberGenerator.SetYear(yearInput.Text);
            if (!keyNumberGenerator.yearReady || yearInput.Text == "Type year")
            {
                yearLabel.BackColor = Color.AntiqueWhite;
            }
            else
            {
                yearLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void writerInput_Enter(object sender, EventArgs e)
        {
            writerLabel.BackColor = Color.WhiteSmoke;
            if (writerInput.Text == "Type name")
            {
                writerInput.Text = "";
            }
        }

        private void writerInput_Leave(object sender, EventArgs e)
        {
            if (writerInput.Text == "")
            {
                writerInput.Text = "Type name";
            }
            keyNumberGenerator.SetWriterI(writerInput.Text);
            if (writerInput.Text == "Type name")
            {
                writerLabel.BackColor = Color.AntiqueWhite;
            }
            else
            {
                writerLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void clientInput_Enter(object sender, EventArgs e)
        {
            clientLabel.BackColor = Color.WhiteSmoke;
            if (clientInput.Text == "Type client name")
            {
                clientInput.Text = "";
            }
        }

        private void clientInput_Leave(object sender, EventArgs e)
        {
            if (clientInput.Text == "")
            {
                clientInput.Text = "Type client name";
            }
            keyNumberGenerator.SetClientI(clientInput.Text);
            if (clientInput.Text == "Type client name")
            {
                clientLabel.BackColor = Color.AntiqueWhite;
            }
            else
            {
                clientLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void reloadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (marketInput.Text == "NULL")
            {
                marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
                marketInput.DropDownStyle = ComboBoxStyle.DropDownList;
                marketLabel.BackColor = Color.WhiteSmoke;
            }
            else
            {
                marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
            }
            if (durationInput.Text == "NULL")
            {
                durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
                durationInput.DropDownStyle = ComboBoxStyle.DropDownList;
                durationLabel.BackColor = Color.WhiteSmoke;
            }
            else
            {
                durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
            }
            if (typeInput.Text == "NULL")
            {
                typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
                typeInput.DropDownStyle = ComboBoxStyle.DropDownList;
                typeLabel.BackColor = Color.WhiteSmoke;
            }
            else
            {
                typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
            }

            yearInput.Text = keyNumberGenerator.Load("year");
            writerInput.Text = keyNumberGenerator.Load("writerInitial");
            clientInput.Text = keyNumberGenerator.Load("clientInitial");
        }

        private void marketPropertiesButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetMarket(marketInput.SelectedItem.ToString());
            keyNumberGenerator.Save("market");
        }

        private void durationPropertiesButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetDuration(int.Parse(durationInput.SelectedItem.ToString()));
            keyNumberGenerator.Save("duration");
        }

        private void typePropertiesButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetType(typeInput.SelectedItem.ToString());
            keyNumberGenerator.Save("type");
        }

        private void yearPropertiesButton_Click(object sender, EventArgs e)
        {
            if (yearInput.Text == "Type year")
            {
                keyNumberGenerator.SetYear(yearInput.Text);
                Properties.Settings.Default.year = yearInput.Text;
            }
            else
            {
                keyNumberGenerator.SetYear(yearInput.Text);
                keyNumberGenerator.Save("year");
            }
        }

        private void writerPropertiesButton_Click(object sender, EventArgs e)
        {
            if (writerInput.Text == "Type name")
            {
                keyNumberGenerator.SetWriterI(writerInput.Text);
                Properties.Settings.Default.writerInitial = writerInput.Text;
            }
            else
            {
                keyNumberGenerator.SetWriterI(writerInput.Text);
                keyNumberGenerator.Save("writerInitial");
            }
        }

        private void clientPropertiesButton_Click(object sender, EventArgs e)
        {
            if (clientInput.Text == "Type year")
            {
                keyNumberGenerator.SetClientI(clientInput.Text);
                Properties.Settings.Default.clientInitial = clientInput.Text;
            }
            else
            {
                keyNumberGenerator.SetClientI(clientInput.Text);
                keyNumberGenerator.Save("clientInitial");
            }
        }

        private void marketNullButton_Click(object sender, EventArgs e)
        {
            marketInput.DropDownStyle = ComboBoxStyle.DropDown;
            marketInput.Text = "NULL";
            keyNumberGenerator.SetMarket(null);
            marketLabel.BackColor = Color.AntiqueWhite;
        }

        private void durationNullButton_Click(object sender, EventArgs e)
        {
            durationInput.DropDownStyle = ComboBoxStyle.DropDown;
            durationInput.Text = "NULL";
            keyNumberGenerator.SetDuration(-1);
            durationLabel.BackColor = Color.AntiqueWhite;
        }

        private void typeNullButton_Click(object sender, EventArgs e)
        {
            typeInput.DropDownStyle = ComboBoxStyle.DropDown;
            typeInput.Text = "NULL";
            keyNumberGenerator.SetType(null);
            typeLabel.BackColor = Color.AntiqueWhite;
        }

        private void marketInput_Enter(object sender, EventArgs e)
        {
            if (marketInput.Text == "NULL")
            {
                marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
                marketInput.DropDownStyle = ComboBoxStyle.DropDownList;
                marketLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void durationInput_Enter(object sender, EventArgs e)
        {
            if (durationInput.Text == "NULL")
            {
                durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
                durationInput.DropDownStyle = ComboBoxStyle.DropDownList;
                durationLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void typeInput_Enter(object sender, EventArgs e)
        {
            if (typeInput.Text == "NULL")
            {
                typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
                typeInput.DropDownStyle = ComboBoxStyle.DropDownList;
                typeLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void copyButton_Click(object sender, EventArgs e)
        {
            System.Windows.Forms.Clipboard.SetText(keyNumberOutput.Text);
        }

        private void marketInput_Leave(object sender, EventArgs e)
        {
            keyNumberGenerator.SetMarket(marketInput.Text);
            if (!keyNumberGenerator.marketReady)
            {
                marketLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void durationInput_Leave(object sender, EventArgs e)
        {
            keyNumberGenerator.SetDuration(int.Parse(durationInput.Text));
            if (!keyNumberGenerator.durationReady)
            {
                durationLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void typeInput_Leave(object sender, EventArgs e)
        {
            keyNumberGenerator.SetType(typeInput.Text);
            if (!keyNumberGenerator.typeReady)
            {
                typeLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void yearInput_TextChanged(object sender, EventArgs e)
        {
            if (yearInput.Text != "Type year")
            {
                yearLabel.BackColor = Color.WhiteSmoke;
                if (yearInput.TextLength != 0)
                {
                    for (int i = 0; i < yearInput.TextLength; i++)
                    {
                        if (!char.IsDigit(yearInput.Text[i]))
                        {
                            yearInput.Text = yearInput.Text.Remove(i, 1);
                            yearLabel.BackColor = Color.AntiqueWhite;
                        }
                    }
                }
            }
        }

        private void keyNumberOutput_TextChanged(object sender, EventArgs e)
        {
            keyNumberLabel.BackColor = Color.WhiteSmoke;
            if (keyNumberOutput.TextLength != 0)
            {
                for (int i = 0; i < keyNumberOutput.TextLength; i++)
                {
                    if (keyNumberOutput.Text[i] != '-' && !char.IsLetterOrDigit(keyNumberOutput.Text[i]))
                    {
                        keyNumberOutput.Text = keyNumberOutput.Text.Remove(i, 1);
                        keyNumberLabel.BackColor = Color.AntiqueWhite;
                    }
                }
            }
        }

        private void keyNumberOutput_Leave(object sender, EventArgs e)
        {
            keyNumberLabel.BackColor = Color.WhiteSmoke;
        }

        private void advancedToolStripMenuItem_Click(object sender, EventArgs e)
        {
            EditProperties popup = new EditProperties();
            DialogResult dialogResult = popup.ShowDialog();
            if (dialogResult == DialogResult.OK)
            {
                if (marketInput.Text == "NULL")
                {
                    if (Array.Exists(KeyNumberGenerator.markets, element => element == keyNumberGenerator.Load("market")))
                    {
                        marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
                    } else
                    {
                        marketInput.SelectedIndex = 0;
                    }
                    marketInput.DropDownStyle = ComboBoxStyle.DropDownList;
                    marketLabel.BackColor = Color.WhiteSmoke;
                }
                else
                {
                    if (Array.Exists(KeyNumberGenerator.markets, element => element == keyNumberGenerator.Load("market")))
                    {
                        marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
                    }
                    else
                    {
                        marketInput.SelectedIndex = 0;
                    }
                }
                if (durationInput.Text == "NULL")
                {
                    if (Array.Exists(KeyNumberGenerator.durations, element => element == int.Parse(keyNumberGenerator.Load("duration"))))
                    {
                        durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));

                    } else
                    {
                        durationInput.SelectedIndex = 0;
                    }
                    durationInput.DropDownStyle = ComboBoxStyle.DropDownList;
                    durationLabel.BackColor = Color.WhiteSmoke;
                }
                else
                {
                    if (Array.Exists(KeyNumberGenerator.durations, element => element == int.Parse(keyNumberGenerator.Load("duration"))))
                    {
                        durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));

                    }
                    else
                    {
                        durationInput.SelectedIndex = 0;
                    }
                }
                if (typeInput.Text == "NULL")
                {
                    if (Array.Exists(KeyNumberGenerator.types, element => element == keyNumberGenerator.Load("type")))
                    {
                        typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
                    } else
                    {
                        typeInput.SelectedIndex = 0;
                    }
                    typeInput.DropDownStyle = ComboBoxStyle.DropDownList;
                    typeLabel.BackColor = Color.WhiteSmoke;
                }
                else
                {
                    if (Array.Exists(KeyNumberGenerator.types, element => element == keyNumberGenerator.Load("type")))
                    {
                        typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
                    }
                    else
                    {
                        typeInput.SelectedIndex = 0;
                    }
                }

                yearInput.Text = keyNumberGenerator.Load("year");
                writerInput.Text = keyNumberGenerator.Load("writerInitial");
                clientInput.Text = keyNumberGenerator.Load("clientInitial");

                if (!keyNumberGenerator.marketReady)
                {
                    marketLabel.BackColor = Color.AntiqueWhite;
                }
                if (!keyNumberGenerator.durationReady)
                {
                    durationLabel.BackColor = Color.AntiqueWhite;
                }
                if (!keyNumberGenerator.typeReady)
                {
                    typeLabel.BackColor = Color.AntiqueWhite;
                }
                if (!keyNumberGenerator.yearReady || yearInput.Text == "Type year")
                {
                    yearLabel.BackColor = Color.AntiqueWhite;
                }
                if (writerInput.Text == "Type name")
                {
                    writerLabel.BackColor = Color.AntiqueWhite;
                }
                if (clientInput.Text == "Type client name")
                {
                    clientLabel.BackColor = Color.AntiqueWhite;
                }
            }
            else if (dialogResult == DialogResult.Cancel) { }
            popup.Dispose();
        }
        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AboutBox popup = new AboutBox();
            DialogResult dialogResult = popup.ShowDialog();

            if (dialogResult == DialogResult.OK)
            {

            }
            popup.Dispose();
        }

        private void updateLabel_Click(object sender, EventArgs e)
        {
            Properties.Settings.Default.firstLaunch = false;
            updateLabel.Visible = false;
        }
    }
}
