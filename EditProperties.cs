using System;
using System.Windows.Forms;

namespace KeyNumberGenerator
{
    public partial class EditProperties : Form
    {
        public EditProperties()
        {
            InitializeComponent();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Properties.Settings.Default.number = 1;
            Properties.Settings.Default.market = "AUG";
            Properties.Settings.Default.year = "Type year";
            Properties.Settings.Default.duration = 10;
            Properties.Settings.Default.type = "R";
            Properties.Settings.Default.clientInitial = "Type client name";
            Properties.Settings.Default.writerInitial = "Type name";
            Properties.Settings.Default.firstLaunch = false;
            Properties.Settings.Default.Save();
            propertyGrid1.Refresh();
        }
    }
}
