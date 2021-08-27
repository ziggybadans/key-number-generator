using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace KeyNumberGenerator
{
    public partial class GUI : Form
    {
        readonly KeyNumberGenerator keyNumberGenerator = new KeyNumberGenerator();

        public GUI()
        {
            InitializeComponent();
            
            marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
            durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
            typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
            yearInput.Text = keyNumberGenerator.Load("year");
            writerInput.Text = keyNumberGenerator.Load("writerInitial");
            clientInput.Text = keyNumberGenerator.Load("clientInitial");
            numberOutput.Text = keyNumberGenerator.Load("number");
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            System.Diagnostics.Process.Start("https://github.com/ziggybadans/key-number-generator");
        }

        private void generateButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetMarket(marketInput.Text);
            keyNumberGenerator.SetYear(yearInput.Text);
            keyNumberGenerator.SetWriterI(writerInput.Text);
            keyNumberGenerator.SetDuration(int.Parse(durationInput.Text));
            keyNumberGenerator.SetType(typeInput.Text);
            keyNumberGenerator.SetClientI(clientInput.Text);

            keyNumberOutput.Text = keyNumberGenerator.Generate();
            numberOutput.Text = keyNumberGenerator.Load("number");
        }

        private void yearInput_Enter(object sender, EventArgs e)
        {
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
            if (!keyNumberGenerator.yearReady)
            {
                yearLabel.BackColor = Color.AntiqueWhite;
            } else
            {
                yearLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void writerInput_Enter(object sender, EventArgs e)
        {
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
        }

        private void clientInput_Enter(object sender, EventArgs e)
        {
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
        }

        private void reloadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
            durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
            typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));

            yearInput.Text = keyNumberGenerator.Load("year");
            writerInput.Text = keyNumberGenerator.Load("writerInitial");
            clientInput.Text = keyNumberGenerator.Load("clientInitial");
            numberOutput.Text = keyNumberGenerator.Load("number");
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
            keyNumberGenerator.SetYear(yearInput.Text);
            keyNumberGenerator.Save("year");
        }

        private void writerPropertiesButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetWriterI(writerInput.Text);
            keyNumberGenerator.Save("writerInitial");
        }

        private void clientPropertiesButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetClientI(clientInput.Text);
            keyNumberGenerator.Save("clientInitial");        
        }

        private void marketNullButton_Click(object sender, EventArgs e)
        {
            marketInput.DropDownStyle = ComboBoxStyle.DropDown;
            marketInput.Text = "NULL";
            keyNumberGenerator.SetMarket(marketInput.Text);
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
            keyNumberGenerator.SetType(typeInput.Text);
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
            System.Windows.Forms.Clipboard.SetText(keyNumberGenerator.keyNumber);
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
    }
}
